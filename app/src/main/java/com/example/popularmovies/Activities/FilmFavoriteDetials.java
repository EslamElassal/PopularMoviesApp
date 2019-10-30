package com.example.popularmovies.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.popularmovies.Adapters.ReviewRecyclerViewAdapter;
import com.example.popularmovies.Adapters.SectionsPagerAdapter;
import com.example.popularmovies.Database.AddFilmViewModel;
import com.example.popularmovies.Database.AddFilmViewModelFactory;
import com.example.popularmovies.Database.AddReviewsViewModel;
import com.example.popularmovies.Database.AddReviewsViewModelFactory;
import com.example.popularmovies.Database.AddTrailersViewModel;
import com.example.popularmovies.Database.AddTrailersViewModelFactory;
import com.example.popularmovies.Database.FilmDatabase;
import com.example.popularmovies.Database.FilmEntry;
import com.example.popularmovies.Database.FilmExcuter;
import com.example.popularmovies.Database.ReviewEntry;
import com.example.popularmovies.Database.TrailerEntry;

import com.example.popularmovies.Fragments.OverViewFragment;
import com.example.popularmovies.Fragments.ReviewsFragment;
import com.example.popularmovies.Fragments.TrailersFragment;
import com.example.popularmovies.Models.Film;
import com.example.popularmovies.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("RedundantCast")
public class FilmFavoriteDetials extends AppCompatActivity {
    private FilmDatabase mDb;
TextView rating , releasedate;
ImageView image,favoriteIcon;
RecyclerView ReviewList;
ReviewRecyclerViewAdapter mAdapter;
ProgressBar mLoadingIndicator;
Intent intent;
String Id;
String Adult;
String Plot;
String Title;
String Image;
 String Rating;
String ReleaseDate;
String Backdropimage;
String Type;
String IsFavorite;
Film Nfilm;
FilmEntry MyFilmEntry;


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

    //RatingBar ratingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detials);
        image=(ImageView)findViewById(R.id.MyFilmImage);
        image.setVisibility(View.INVISIBLE);
        rating=(TextView)findViewById(R.id.MyFilmRating);
        rating.setVisibility(View.INVISIBLE);
        releasedate=(TextView)findViewById(R.id.MyFilmReleaseDate);
        releasedate.setVisibility(View.INVISIBLE);
        mLoadingIndicator=(ProgressBar) findViewById(R.id.pb_loading_indicator_Detialed_Activity);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        favoriteIcon=(ImageView) findViewById(R.id.favoriteIcon);
        favoriteIcon.setTag(R.drawable.favorite);
        favoriteIcon.setImageResource(R.drawable.favorite);
        favoriteIcon.setVisibility(View.INVISIBLE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.INVISIBLE);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setVisibility(View.INVISIBLE);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setVisibility(View.INVISIBLE);
        Intent a = getIntent();
        if(a.hasExtra("id"))
        {
            Id=a.getStringExtra("id");
        }
                mDb = FilmDatabase.getInstance(getApplicationContext());

        tabLayout.setupWithViewPager(viewPager);

        getFilmFromDataBaseToActivity();
        getFilmReviews();
        getFilmTrailers();
    }


  void getFilmTrailers()
    {


        AddTrailersViewModelFactory factory = new AddTrailersViewModelFactory(mDb, Id);
        final AddTrailersViewModel viewModel
                = ViewModelProviders.of(this, factory).get(AddTrailersViewModel.class);
        viewModel.getTrailers().observe(this, new Observer<List<TrailerEntry>>() {
            @Override
            public void onChanged(List<TrailerEntry> trailerEntries) {

                if(trailerEntries!=null) {
                    viewModel.getTrailers().removeObserver(this);


                    final String Trailers[][] = new String[trailerEntries.size()][2];
                    for(int i =0;i<trailerEntries.size();i++)
                    {
                        Trailers[i][0]=trailerEntries.get(i).getTrailer_name();
                        Trailers[i][1]=trailerEntries.get(i).getTrailer_link();

                    }

                    TrailersFragment fragment = new TrailersFragment();
                    fragment.setTrailersArr(Trailers);
                    adapter.addFragment(fragment,  "Trailers");
                     viewPager.setAdapter(adapter);

                    mLoadingIndicator.setVisibility(View.INVISIBLE);
                    toolbar.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                    tabLayout.setVisibility(View.VISIBLE);



                }
            }
        });


     }

   void getFilmReviews()

    {

        AddReviewsViewModelFactory factory = new AddReviewsViewModelFactory(mDb, Id);
        final AddReviewsViewModel viewModel
                = ViewModelProviders.of(this, factory).get(AddReviewsViewModel.class);
        viewModel.getReviews().observe(this, new Observer<List<ReviewEntry>>() {
            @Override
            public void onChanged(List<ReviewEntry> reviewEntries) {

                 if(reviewEntries!=null) {
                    viewModel.getReviews().removeObserver(this);

                    final String Reviews[][] = new String[reviewEntries.size()][3];
                    for(int i =0;i<reviewEntries.size();i++)
                    {
                        Reviews[i][0]=reviewEntries.get(i).getReview_author_name();
                        Reviews[i][1]=reviewEntries.get(i).getReview_link();
                        Reviews[i][2]=reviewEntries.get(i).getReview_content();

                    }
                     ReviewsFragment fragment = new ReviewsFragment();
                    fragment.setReviewsArr(Reviews);
                     adapter.addFragment(fragment,  "Reviews");
                     viewPager.setAdapter(adapter);

                 }

             }
        });



    }

    void getFilmFromDataBaseToActivity() {
          AddFilmViewModelFactory factory = new AddFilmViewModelFactory(mDb, Id);
        final AddFilmViewModel viewModel
                = ViewModelProviders.of(this, factory).get(AddFilmViewModel.class);
        viewModel.getFilm().observe(this, new Observer<FilmEntry>() {
                    @Override
                    public void onChanged(FilmEntry filmEntry) {
                        Log.e("ESLAM","KosOmak : "+filmEntry);
                        if (filmEntry != null) {
                            Log.e("ESLAM","Fuck : "+filmEntry);
                            viewModel.getFilm().removeObserver(this);


                            Picasso.with(FilmFavoriteDetials.this)
                                    .load(filmEntry.getImage())
                                    .into(image);

                            rating.setText(filmEntry.getRating());
                            releasedate.setText(filmEntry.getReleasedate());
                            setTitle(filmEntry.getTitle());

                            image.setVisibility(View.VISIBLE);
                            rating.setVisibility(View.VISIBLE);
                            releasedate.setVisibility(View.VISIBLE);
                            favoriteIcon.setVisibility(View.VISIBLE);
                            OverViewFragment fragment=new OverViewFragment();
                            fragment.setOverViewText(filmEntry.getPlot());
                            adapter.addFragment(fragment, "Overview");
                            viewPager.setAdapter(adapter);
                        }
                    }
                }
        );

     }



    public void onFavoriteButtonClicked(View view) {

        favoriteIcon.setImageResource(R.drawable.unfovorite);
        favoriteIcon.setTag(R.drawable.unfovorite);
              FilmExcuter.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {

                     mDb.filmDao().delelteFilm(Id);
                     mDb.reviewDao().delelteReviewsByFilmId(Id);
                     mDb.trailerDao().delelteTrailersByFilmId(Id);



                }
            });

            Toast.makeText(FilmFavoriteDetials.this,"Removed from Favorites",Toast.LENGTH_LONG).show();

            finish();


    }





}
