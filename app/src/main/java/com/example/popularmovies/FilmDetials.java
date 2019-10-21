package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;

public class FilmDetials extends AppCompatActivity {
TextView plot,rating , releasedate;
ImageView image;
ProgressBar mLoadingIndicator;
Intent intent;
String Id;
String Title;
String Image;
String Plot;
String Rating;
String ReleaseDate;
Film Nfilm;
    private static final String FILM_REVIEWS_LINK_PART1 ="http://api.themoviedb.org/3/movie/";
    private static final String FILM_REVIEWS_LINK_PART2 ="/reviews?api_key=b9ef75ce7022c8417b4335cb4551ad86";

    private static final String FILM_TRAILERS_PART_1 ="http://api.themoviedb.org/3/movie/";
    private static final String FILM_TRAILERS_PART_2 ="/trailers?api_key=b9ef75ce7022c8417b4335cb4551ad86";

    private static final String FILM_DETAILS_PART_1 ="https://api.themoviedb.org/3/movie/";
    private static final String FILM_DETAILS_PART_2 ="?api_key=b9ef75ce7022c8417b4335cb4551ad86";

    //RatingBar ratingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detials);
        image=(ImageView)findViewById(R.id.MyFilmImage);
        plot=(TextView)findViewById(R.id.MyFilmPlot);
        rating=(TextView)findViewById(R.id.MyFilmRating);
        releasedate=(TextView)findViewById(R.id.MyFilmReleaseDate);
         mLoadingIndicator=(ProgressBar) findViewById(R.id.pb_loading_indicator_Detialed_Activity);
        intent =getIntent();
        if(intent.hasExtra("id"))
        {
           Id=intent.getStringExtra("id");
        }
        if(intent.hasExtra("plot"))
        {Plot=intent.getStringExtra("plot");
            plot.setText(Plot);
        }

        if(intent.hasExtra("rating"))
        {
           /* float num = Float.parseFloat(intent.getStringExtra("rating"));
            num=num/2.0f;
            ratingbar.setRating(num);
             //to not change bar value when user touch it
            ratingbar.setIsIndicator(true);*/
           Rating=intent.getStringExtra("rating");
             rating.setText(Rating);
        }

        if(intent.hasExtra("releasedate"))
        {
            ReleaseDate=intent.getStringExtra("releasedate");
            releasedate.setText(ReleaseDate);
        }
       /* if(intent.hasExtra("image"))
        {
            Image=intent.getStringExtra("image");
            Picasso.with(this)
                    .load(Image)
                    .into(image);
         }*/
        //to set Activity Tilte
       Title= intent.getStringExtra("title");
        setTitle(Title);
        loadFilmsData(FILM_DETAILS_PART_1+""+Id+""+FILM_DETAILS_PART_2);


    }


    private void loadFilmsData(String URL_QUERY) {

        new FetchFilmDetailsTask().execute(URL_QUERY);
    }

    // COMPLETED (5) Create a class that extends AsyncTask to perform network requests
    public class FetchFilmDetailsTask extends AsyncTask<String, Void, Film> {

        // COMPLETED (6) Override the doInBackground method to perform your network requests
        @Override
        protected Film doInBackground(String... params) {

            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String URL_QUERY = params[0];
            URL FilmsRequestUrl = NetworkUtils.buildUrl(URL_QUERY);

            try {
                String jsonFilmResponse = NetworkUtils
                        .getResponseFromHttpUrl(FilmsRequestUrl);

                Film FilmsJsonData = GetFilmsJsonData.getSimpleEachFilmDetailsStringsFromJson(FilmDetials.this, jsonFilmResponse);

                return FilmsJsonData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }
        // COMPLETED (7) Override the onPostExecute method to display the results of the network request
        @Override
        protected void onPostExecute(Film FilmsData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (FilmsData != null) {
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */


                Nfilm=FilmsData;
                CompleteFilmDetails();
            }
        }
    }

void CompleteFilmDetails()
{    String asd=Nfilm.getBackDrop_Image();
    Picasso.with(this)
        .load(Nfilm.getBackDrop_Image())
        .into(image);
}
}
