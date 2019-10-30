package com.example.popularmovies.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmovies.Adapters.ReviewRecyclerViewAdapter;
import com.example.popularmovies.Adapters.SectionsPagerAdapter;
import com.example.popularmovies.Database.AddFilmViewModel;
import com.example.popularmovies.Database.AddFilmViewModelFactory;
import com.example.popularmovies.Database.FilmDatabase;
import com.example.popularmovies.Database.FilmEntry;
import com.example.popularmovies.Database.FilmExcuter;
import com.example.popularmovies.Database.ReviewEntry;
import com.example.popularmovies.Database.TrailerEntry;

import com.example.popularmovies.Fragments.OverViewFragment;
import com.example.popularmovies.Fragments.ReviewsFragment;
import com.example.popularmovies.Fragments.TrailersFragment;
import com.example.popularmovies.Models.Film;
import com.example.popularmovies.Network.GetFilmsJsonData;
import com.example.popularmovies.Network.NetworkUtils;
import com.example.popularmovies.R;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FilmDetials extends AppCompatActivity {
    private FilmDatabase mDb;
TextView rating , releasedate;
ImageView image,favoriteIcon;
RecyclerView ReviewList;
ReviewRecyclerViewAdapter mAdapter;
ProgressBar mLoadingIndicator;
Intent intent;
String Id;
String Plot;
String Title;
String Image;
 String Rating;
String ReleaseDate;
String IsFavorite;
Film Nfilm;
FilmEntry MyFilmEntry;

    private static final String FILM_REVIEWS_LINK_PART1 ="http://api.themoviedb.org/3/movie/";
    private static final String FILM_REVIEWS_LINK_PART2 ="/reviews?api_key=b9ef75ce7022c8417b4335cb4551ad86";

    private static final String FILM_TRAILERS_PART_1 ="http://api.themoviedb.org/3/movie/";
    private static final String FILM_TRAILERS_PART_2 ="/trailers?api_key=b9ef75ce7022c8417b4335cb4551ad86";

    private static final String FILM_DETAILS_PART_1 ="https://api.themoviedb.org/3/movie/";
    private static final String FILM_DETAILS_PART_2 ="?api_key=b9ef75ce7022c8417b4335cb4551ad86";


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //RatingBar ratingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detials);
        image=(ImageView)findViewById(R.id.MyFilmImage);
         rating=(TextView)findViewById(R.id.MyFilmRating);
        releasedate=(TextView)findViewById(R.id.MyFilmReleaseDate);
         mLoadingIndicator=(ProgressBar) findViewById(R.id.pb_loading_indicator_Detialed_Activity);
        favoriteIcon=(ImageView) findViewById(R.id.favoriteIcon);
        favoriteIcon.setTag(R.drawable.unfovorite);
        favoriteIcon.setVisibility(View.INVISIBLE);
         intent =getIntent();
        if(intent.hasExtra("id"))
        {
           Id=intent.getStringExtra("id");
        }
        if(intent.hasExtra("plot"))
        {Plot=intent.getStringExtra("plot");

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
        if(intent.hasExtra("image"))
        {
            Image=intent.getStringExtra("image");

         }
        if(intent.hasExtra("isfavorite"))
        {
            IsFavorite=intent.getStringExtra("isfavorite");
        }
        //to set Activity Tilte
       Title= intent.getStringExtra("title");
        setTitle(Title);
        String []Data=new String[3];
        Data[0]=FILM_DETAILS_PART_1+""+Id+""+FILM_DETAILS_PART_2;
        Data[1]=FILM_REVIEWS_LINK_PART1+""+Id+""+FILM_REVIEWS_LINK_PART2;
        Data[2]=FILM_TRAILERS_PART_1+""+Id+""+FILM_TRAILERS_PART_2;

        loadFilmsData(Data);


        toolbar = (Toolbar) findViewById(R.id.toolbar);




        viewPager = (ViewPager) findViewById(R.id.view_pager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mDb = FilmDatabase.getInstance(getApplicationContext());




    }
    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

         adapter.addFragment(new OverViewFragment(Plot), "Overview");
        adapter.addFragment(new ReviewsFragment(Nfilm.getReviews()), "Reviews");
        adapter.addFragment(new TrailersFragment(Nfilm.getTrailers()), "Trailers");
        viewPager.setAdapter(adapter);
    }


    List<TrailerEntry> getFilmTrailers()
    {
        List<TrailerEntry> entries = new ArrayList<>();
        String trailers [][]=Nfilm.getTrailers();
        for(int i=0;i<trailers.length;i++)
        {
            TrailerEntry entry = new TrailerEntry(Id,trailers[i][0],trailers[i][1]);
           entries.add(i,entry);
        }
        return entries;
    }

    List<ReviewEntry> getFilmReviews()

    {
        List<ReviewEntry> entries = new ArrayList<>();
        String reviews [][]=Nfilm.getReviews();
        for(int i=0;i<reviews.length;i++)
        {
            ReviewEntry entry = new ReviewEntry(Id,reviews[i][0],reviews[i][1],reviews[i][2]);
            entries.add(i,entry);
        }
        return entries;
    }
    private void loadFilmsData(String []URLS_QUERY) {

        new FetchFilmDetailsTask().execute(URLS_QUERY);
    }

    public void onFavoriteButtonClicked(View view) {
        Integer resource = (Integer) favoriteIcon.getTag();
        String id = Nfilm.getId();
        String title = Nfilm.getTitle();
        String image = Nfilm.getImage();
        String plot = Nfilm.getPlot();
        String rating = Nfilm.getRating();
        String releasedate = Nfilm.getReleaseDate();
        String adult = Nfilm.getAdult();
        String type = Nfilm.getType();
        String backdropimage = Nfilm.getBackDrop_Image();
        String isFavorite="true";

        final FilmEntry film = new FilmEntry(id,title,image,
                plot,rating,releasedate,adult,
                type,backdropimage,isFavorite);

        if(this.IsFavorite.equals("false")){

            FilmExcuter.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {

                    List<ReviewEntry>reviewEntries = getFilmReviews();
                    List<TrailerEntry>trailerEntries=getFilmTrailers();
                    mDb.filmDao().insertFilm(film);
                      mDb.reviewDao().insertReviews(reviewEntries);
                      mDb.trailerDao().insertTrailers(trailerEntries);


                }
            });
            favoriteIcon.setImageResource(R.drawable.favorite);
            favoriteIcon.setTag(R.drawable.favorite);
            IsFavorite="true";

            Toast.makeText(FilmDetials.this,"Saved to Favorites",Toast.LENGTH_LONG).show();

        }else {



             FilmExcuter.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {

                     mDb.filmDao().delelteFilm(Id);
                     mDb.reviewDao().delelteReviewsByFilmId(Id);
                     mDb.trailerDao().delelteTrailersByFilmId(Id);



                }
            });
            favoriteIcon.setImageResource(R.drawable.unfovorite);
            favoriteIcon.setTag(R.drawable.unfovorite);
            IsFavorite="false";
            Toast.makeText(FilmDetials.this,"Removed from Favorites",Toast.LENGTH_LONG).show();

        }

             }
    // COMPLETED (5) Create a class that extends AsyncTask to perform network requests
    public class FetchFilmDetailsTask extends AsyncTask<String[], Void, Film> {

        // COMPLETED (6) Override the doInBackground method to perform your network requests


        @Override
        protected Film doInBackground(String[]... params) {
            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String Detail_URL_QUERY = params[0][0];
            String Reviews_URL_QUERY = params[0][1];
            String Trailers_URL_QUERY = params[0][2];

            URL FilmsRequestUrlDetails = NetworkUtils.buildUrl(Detail_URL_QUERY);
            URL FilmsRequestUrlReviews = NetworkUtils.buildUrl(Reviews_URL_QUERY);
            URL FilmsRequestUrlTrailers = NetworkUtils.buildUrl(Trailers_URL_QUERY);

            try {
                String jsonFilmResponseDetials = NetworkUtils
                        .getResponseFromHttpUrl(FilmsRequestUrlDetails);

                String jsonFilmResponseReviews = NetworkUtils
                        .getResponseFromHttpUrl(FilmsRequestUrlReviews);

                String jsonFilmResponseTrailers = NetworkUtils
                        .getResponseFromHttpUrl(FilmsRequestUrlTrailers);


                Film FilmsJsonDataDetails = GetFilmsJsonData.getSimpleEachFilmDetailsStringsFromJson(FilmDetials.this, jsonFilmResponseDetials);
                String [][]FilmsJsonDataReviews = GetFilmsJsonData.getReviewsStringFromJson(FilmDetials.this, jsonFilmResponseReviews);
                String[][] FilmsJsonDataTrailers = GetFilmsJsonData.getTrailersStringFromJson(FilmDetials.this, jsonFilmResponseTrailers);
                Film FilmsJsonData = new Film();

                FilmsJsonData.setBackDrop_Image(FilmsJsonDataDetails.getBackDrop_Image());
                FilmsJsonData.setAdult(FilmsJsonDataDetails.getAdult());
                FilmsJsonData.setType(FilmsJsonDataDetails.getType());
                FilmsJsonData.setReviews(FilmsJsonDataReviews);
                FilmsJsonData.setTrailers(FilmsJsonDataTrailers);


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
                Nfilm.setId(Id);
                Nfilm.setTitle(Title);
                Nfilm.setReleaseDate(ReleaseDate);
                Nfilm.setRating(Rating);
                Nfilm.setPlot(Plot);
                Nfilm.setImage(Image);
                CompleteFilmDetails();
            }
        }
    }

void CompleteFilmDetails()
{    String asd=Nfilm.getBackDrop_Image();
    Picasso.with(this)
        .load(Nfilm.getBackDrop_Image())
        .into(image);

    loadDatabaseToCheckFavortieOrNot();
    setupViewPager(viewPager);


}
void loadDatabaseToCheckFavortieOrNot()
{

       AddFilmViewModelFactory factory = new AddFilmViewModelFactory(mDb, Id);
       final AddFilmViewModel viewModel
               = ViewModelProviders.of(this, factory).get(AddFilmViewModel.class);
      viewModel.getFilm().observe(this, new Observer<FilmEntry>() {
           @Override
           public void onChanged(FilmEntry filmEntry) {

              if(filmEntry!=null)
              {MyFilmEntry=filmEntry;
                  viewModel.getFilm().removeObserver(this);
               IsFavorite=filmEntry.getIsfavorite();
               if(IsFavorite.equals("true"))
               {
                   favoriteIcon.setImageResource(R.drawable.favorite);


               }
               else  if(IsFavorite.equals("false"))
               {
                   favoriteIcon.setImageResource(R.drawable.unfovorite);

               }

           }
              else{
                  favoriteIcon.setImageResource(R.drawable.unfovorite);
              }
           }
       });
    favoriteIcon.setVisibility(View.VISIBLE);

}


}
