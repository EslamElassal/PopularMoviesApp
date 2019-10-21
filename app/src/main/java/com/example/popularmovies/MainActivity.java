package com.example.popularmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements FilmsAdapter.ListItemClickListener {
    private RecyclerView FilmRecyclerView;
    ProgressBar mLoadingIndicator;
    FilmsAdapter filmAdapter;
    Film [] Nfilms =null;
    private static final String POPULAR_FILMS_URL =
            "http://api.themoviedb.org/3/movie/popular?api_key=b9ef75ce7022c8417b4335cb4551ad86";

    private static final String TOP_RATED_FILMS_URL =
            "http://api.themoviedb.org/3/movie/top_rated?api_key=b9ef75ce7022c8417b4335cb4551ad86";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        FilmRecyclerView=(RecyclerView)findViewById(R.id.RecyelerViewFilms);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //FilmRecyclerView.setLayoutManager(layoutManager);
        int numberOfColumns=2;
        FilmRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        FilmRecyclerView.setHasFixedSize(true);

         /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
       // filmAdapter = new FilmsAdapter(Nfilms,this);
        //FilmRecyclerView.setAdapter(null);


        loadFilmsData(POPULAR_FILMS_URL);
    }


    private void loadFilmsData(String URL_QUERY) {

        new FetchFilmsTask().execute(URL_QUERY);
    }

    // COMPLETED (5) Create a class that extends AsyncTask to perform network requests
    public class FetchFilmsTask extends AsyncTask<String, Void, Film[]> {

        // COMPLETED (6) Override the doInBackground method to perform your network requests
        @Override
        protected Film[] doInBackground(String... params) {

            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String URL_QUERY = params[0];
            URL FilmsRequestUrl = NetworkUtils.buildUrl(URL_QUERY);

            try {
                String jsonFilmResponse = NetworkUtils
                        .getResponseFromHttpUrl(FilmsRequestUrl);

                Film[] FilmsJsonData = GetFilmsJsonData.getSimpleFilmStringsFromJson(MainActivity.this, jsonFilmResponse);

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
        protected void onPostExecute(Film[] FilmsData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (FilmsData != null) {
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */
                Nfilms=FilmsData;
                filmAdapter = new FilmsAdapter(FilmsData,MainActivity.this);
                FilmRecyclerView.setAdapter(filmAdapter);
            }
        }
    }





    @Override
    public void onListItemClick(int item) {
         Intent intent = new Intent(MainActivity.this, FilmDetials.class);
        intent.putExtra("id",Nfilms[item].getId());
         intent.putExtra("plot",Nfilms[item].getPlot());
        intent.putExtra("image",Nfilms[item].getImage());
        intent.putExtra("title",Nfilms[item].getTitle());
        intent.putExtra("releasedate",Nfilms[item].getReleaseDate());
        intent.putExtra("rating",Nfilms[item].getRating());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.popularity_menu_item)
        {
//if checked then another item unchecked because there are in one group , in one group one item only checked
// at one time
                item.setChecked(true);
            loadFilmsData(POPULAR_FILMS_URL);
            setTitle("Popular Movies");

        }

        else if(item.getItemId()==R.id.top_rated_menu_item)
        {

               item.setChecked(true);
            loadFilmsData(TOP_RATED_FILMS_URL);
            setTitle("Top Rated");


        }
         return super.onOptionsItemSelected(item);
    }
}
