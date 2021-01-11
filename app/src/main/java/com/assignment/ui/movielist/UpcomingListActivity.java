package com.assignment.ui.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.R;
import com.assignment.api.MovielistAPI;
import com.assignment.model.ListModel;
import com.assignment.utils.Common;
import com.assignment.utils.Config;

import java.net.URLEncoder;
import java.util.ArrayList;

public class UpcomingListActivity extends Activity {


    //region VARIABLE
    private static final String TAG = "UpcomingMovieListActivity";
    private RecyclerView recyclerViewUpcomingMovie;
    private String mUrl;
    ArrayList<ListModel> items;
    private ViewAdapter adapter;
    private ImageView img_search;
    //endregion

    //region onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);
        recyclerViewUpcomingMovie= findViewById(R.id.recyclerViewUpcomingMovie);
        img_search = findViewById(R.id.img_search);
        // get the reference of RecyclerView
        // set a LinearLayoutManager with default vertical orientation
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUpcomingMovie.setLayoutManager(linearLayoutManager);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(UpcomingListActivity.this,SearchMovieListActivity.class);
                startActivity(searchIntent);
            }
        });
    }
    //endregion

    //region onResume

    @Override
    protected void onResume() {
        super.onResume();
      loadData();
    }
    private void loadData() {
        mUrl = Config.UpcomingList;

        func(mUrl);
    }
    //endregion

    //region API Calling
    @SuppressLint("LongLogTag")
    public void func(String url) {
        try {
            Log.i(TAG, "-------Search---0000-------" + url);
            if (Common.isConnectingToInternet(UpcomingListActivity.this)) {
                new MovielistAPI(upcomingmovieAPIListener).getMovieList(UpcomingListActivity.this, url);
            } else {
                Toast.makeText(this, "No Data Available. Please wait", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected MovielistAPI.MovielistAPIListListener upcomingmovieAPIListener = new MovielistAPI.MovielistAPIListListener() {


        @Override
        public void requestSuccessful(ArrayList<ListModel> models) {
            try {

                items = models;

                if (adapter == null) {
                    adapter = new ViewAdapter(new OnItemClickListener() {
                        @Override
                        public void onItemClick(ListModel model) {
                            Intent intent = new Intent(UpcomingListActivity.this, MovieDetailActivity.class);
                            intent.putExtra("DetailModel", model);
                            startActivity(intent);
                        }
                    });
                    recyclerViewUpcomingMovie.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onLoadFail() {

        }
    };

        //endregion


    public interface OnItemClickListener {
        void onItemClick(ListModel model);
    }

    //region UPCOMING APPOINTMENT LIST ADAPTER
    public class ViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private OnItemClickListener listener = null;

        public ViewAdapter(OnItemClickListener listener) {
            this.listener = listener;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_movie, parent, false);
            return new ViewHolder(layoutView);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof ViewHolder) {
                final ListModel upcomingModel = items.get(position);

                ((ViewHolder) holder).tv_movie_title.setText(upcomingModel.title);
                ((ViewHolder) holder).tv_release_date.setText(upcomingModel.release_date);
                ((ViewHolder) holder).tv_other_details.setText(upcomingModel.overview);

                ((ViewHolder) holder).bind(upcomingModel, listener);
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_movie_title, tv_release_date, tv_other_details;
        private LinearLayout layout;
        private ImageView path;
        private TextView btnplay;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_movie_title = (TextView) itemView.findViewById(R.id.tv_movie_title);
            tv_release_date = (TextView) itemView.findViewById(R.id.tv_release_date);
            tv_other_details = (TextView) itemView.findViewById(R.id.tv_other_details);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            path = (ImageView) itemView.findViewById(R.id.path);
            btnplay =(TextView) itemView.findViewById(R.id.btnplay);
        }

        public void bind(final ListModel model, final OnItemClickListener listener) {
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(model);
                }
            });
        }
    }
    //endregion
}