package com.assignment.ui.movielist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.assignment.R;
import com.assignment.adpter.EntryAdapter;
import com.assignment.api.MovielistAPI;
import com.assignment.model.Item;
import com.assignment.model.ListModel;
import com.assignment.model.SectionItem;
import com.assignment.utils.Common;
import com.assignment.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;


public class SearchMovieListActivity extends Activity implements AdapterView.OnItemClickListener {


    //region VARIABLE
    private static final String TAG = "MovieListActivity";
    private EntryAdapter mAdapter;
    private EditText txt_search;
    private ListView listview;
    private TextView txt_cancel;
    private Boolean status = false;
    private CharSequence str;
    private String mUrl;
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<ListModel> DetailModel =new ArrayList<>();
    //endregion

    //region ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);

        txt_search = (EditText) findViewById(R.id.txt_search);
        listview = (ListView) findViewById(R.id.listView_main);
        txt_search = (EditText) findViewById(R.id.txt_search);
        txt_search.setFocusable(true);
        txt_cancel = (TextView) findViewById(R.id.txtcancel);

        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    txt_search.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                str = s;
                if (filterLongEnough() && !status && (str != null || !TextUtils.isEmpty(str))) {
                    status = true;
                    loadData(String.valueOf(str));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        txt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (str == null || TextUtils.isEmpty(str)) {

                    } else {
                        status = true;
                        loadData(String.valueOf(str));
                    }
                }
                return false;
            }
        });


        mAdapter = new EntryAdapter(this, items);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(this);

    }

    private void loadData(String strsearch) {
        String mStr = URLEncoder.encode(strsearch);
        mUrl = Config.MovieList+mStr+Config.page;

        func(mUrl);
    }


    public boolean filterLongEnough() {
        return txt_search.getText().toString().trim().length() >= 5;
    }

    public void func(String url) {
        try {
            items.clear();
            Log.i(TAG, "-------Search---0000-------" + url);
            if (Common.isConnectingToInternet(SearchMovieListActivity.this)) {
                new MovielistAPI(movieAPIListener).getMovieList(SearchMovieListActivity.this, url);
            } else {
                txt_search.setClickable(true);
                status = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected MovielistAPI.MovielistAPIListListener movieAPIListener = new MovielistAPI.MovielistAPIListListener() {


        @Override
        public void requestSuccessful(ArrayList<ListModel> models) {
            try
            {
                DetailModel =models;
                Log.i(TAG, "-------response---0000-------" + models);
                for (int i = 0; i < models.size(); i++) {
                    if (models.size() > 0) {
                    final ListModel searchModel = models.get(i);
                    String title = "";
                    JSONObject json = new JSONObject();

                    json.put("title", searchModel.title);


                    title = json.toString();
                    System.out.println(title);
                    ArrayList<Item> home_arr = parseJson(json);
                        if (home_arr.isEmpty()) {
                            txt_search.setClickable(true);
                            Log.i(TAG, "-------response----if------");
                            listview.setVisibility(View.VISIBLE);

                        } else {
                            txt_search.setClickable(true);
                            Log.i(TAG, "-------response----else------");
                            listview.setVisibility(View.VISIBLE);

                            //tvEmptyView.setVisibility(View.GONE);
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    mAdapter.notifyDataSetChanged();
                                }
                            });

                        }

                    } else {

                    }

                }


            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onLoadFail() {
        }
    };



    private ArrayList<Item> parseJson(JSONObject response) {
        try {
            status = false;
            items.clear();
            mAdapter.notifyDataSetChanged();
            //ArrayList<AssetDataModel> assetDataModelArrayList = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                try {
                    Log.i(TAG, "-------------Search Response-------" + response.names().get(i));
                    Object str = response.names().get(i);
                    Log.i(TAG, "-------------Search 000-------" + str);
                    if (response.names().get(i).equals("title")) {
                        items.add(new SectionItem(response.get("title").toString()));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(SearchMovieListActivity.this, MovieDetailActivity.class);
        intent.putExtra("DetailModel", DetailModel);
        startActivity(intent);
    }
    //endregion
}