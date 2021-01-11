package com.assignment.ui.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.R;
import com.assignment.model.ListModel;

public class MovieDetailActivity extends Activity {

    private ListModel DetailModel;
    private TextView tv_movie_title,tv_release_date,tv_other_details,tv_Language;
    private ImageView img_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getParcelableExtra("DetailModel") != null)
            DetailModel = getIntent().getParcelableExtra("DetailModel");

        setContentView(R.layout.activity_movie_detail);
        tv_movie_title= findViewById(R.id.txt_movie_title);
        tv_release_date = findViewById(R.id.txt_release_date);
        tv_other_details =findViewById(R.id.txt_other_details);
        tv_Language =findViewById(R.id.txt_Language);

        tv_movie_title.setText(DetailModel.title);
        tv_release_date.setText(DetailModel.release_date);
        tv_other_details.setText(DetailModel.overview);

        if(DetailModel.language.equals("en")){
        tv_Language.setText("English");}
        else if(DetailModel.language.equals("hi")){
            tv_Language.setText("Hindi");
        }

        img_search = findViewById(R.id.img_search);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(MovieDetailActivity.this,SearchMovieListActivity.class);
                startActivity(searchIntent);
            }
        });

    }
}