package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FilmDetials extends AppCompatActivity {
TextView plot,rating , releasedate;
ImageView image;
Intent intent;
//RatingBar ratingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detials);
        image=(ImageView)findViewById(R.id.MyFilmImage);
        plot=(TextView)findViewById(R.id.MyFilmPlot);
        rating=(TextView)findViewById(R.id.MyFilmRating);
        releasedate=(TextView)findViewById(R.id.MyFilmReleaseDate);
       // ratingbar=(RatingBar)findViewById(R.id.MyRatingBar);
        intent =getIntent();
        if(intent.hasExtra("plot"))
        {
            plot.setText(intent.getStringExtra("plot"));
        }

        if(intent.hasExtra("rating"))
        {
           /* float num = Float.parseFloat(intent.getStringExtra("rating"));
            num=num/2.0f;
            ratingbar.setRating(num);
             //to not change bar value when user touch it
            ratingbar.setIsIndicator(true);*/
             rating.setText(intent.getStringExtra("rating"));
        }

        if(intent.hasExtra("releasedate"))
        {
            releasedate.setText(intent.getStringExtra("releasedate"));
        }
        if(intent.hasExtra("image"))
        {
            Picasso.with(this)
                    .load(intent.getStringExtra("image"))
                    .into(image);
         }
        //to set Activity Tilte
        setTitle(intent.getStringExtra("title"));
    }


}
