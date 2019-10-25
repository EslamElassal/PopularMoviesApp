package com.example.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        favoriteIcon=(ImageView) findViewById(R.id.favoriteIcon);
        favoriteIcon.setTag(R.drawable.unfovorite);
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

                loadFilmsDataFromDatabase();

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

         final List<TrailerEntry> entries = new ArrayList<>();

        AddTrailersViewModelFactory factory = new AddTrailersViewModelFactory(mDb, Id);
        final AddTrailersViewModel viewModel
                = ViewModelProviders.of(this, factory).get(AddTrailersViewModel.class);
        viewModel.getTrailers().observe(this, new Observer<List<TrailerEntry>>() {
            @Override
            public void onChanged(List<TrailerEntry> trailerEntries) {

                if(trailerEntries!=null) {
                    viewModel.getTrailers().removeObserver(this);

                    for(int i=0;i<trailerEntries.size();i++)
                    {
                    entries.add(i,trailerEntries.get(i));

                    }

                }
            }
        });


        return entries;
    }

   List<ReviewEntry> getFilmReviews()

    {
        final List<ReviewEntry> entries = new ArrayList<>();

        AddReviewsViewModelFactory factory = new AddReviewsViewModelFactory(mDb, Id);
        final AddReviewsViewModel viewModel
                = ViewModelProviders.of(this, factory).get(AddReviewsViewModel.class);
        viewModel.getReviews().observe(this, new Observer<List<ReviewEntry>>() {
            @Override
            public void onChanged(List<ReviewEntry> reviewEntries) {

                if(reviewEntries!=null) {
                    viewModel.getReviews().removeObserver(this);

                    for(int i=0;i<reviewEntries.size();i++)
                    {
                        entries.add(i,reviewEntries.get(i));

                    }

                }
                else
                {reviewEntries.size();
                reviewEntries.size();
                }
            }
        });


        return entries;
    }

    void getFilmFromDataBaseToActivity() {
          AddFilmViewModelFactory factory = new AddFilmViewModelFactory(mDb, Id);
        final AddFilmViewModel viewModel
                = ViewModelProviders.of(this, factory).get(AddFilmViewModel.class);
        viewModel.getFilm().observe(this, new Observer<FilmEntry>() {
                    @Override
                    public void onChanged(FilmEntry filmEntry) {

                        if (filmEntry != null) {
                            viewModel.getFilm().removeObserver(this);
                          Adult= filmEntry.getAdult();
                            Backdropimage=filmEntry.getBackdropimage();
                            Image= filmEntry.getImage();
                            Plot =filmEntry.getPlot();
                            Rating=filmEntry.getRating();
                            Title=filmEntry.getTitle();
                            Type=filmEntry.getType() ;
                            ReleaseDate=filmEntry.getReleasedate();
                            Id=filmEntry.getId();
                           IsFavorite=filmEntry.getIsfavorite();
                             MyFilmEntry=filmEntry;


                            Picasso.with(FilmFavoriteDetials.this)
                                    .load(filmEntry.getImage())
                                    .into(image);

                            rating.setText(filmEntry.getRating());
                            releasedate.setText(filmEntry.getReleasedate());
                            setTitle(filmEntry.getTitle());



                        }
                    }
                }
        );

     }

    private void loadFilmsDataFromDatabase( ) {
         List<ReviewEntry>entries=getFilmReviews();

         final String Reviews[][] = new String[entries.size()][3];
        for(int i =0;i<entries.size();i++)
        {
            Reviews[i][0]=entries.get(i).getReview_author_name();
            Reviews[i][1]=entries.get(i).getReview_link();
            Reviews[i][2]=entries.get(i).getReview_content();

        }


        List<TrailerEntry>entriesTrailers=getFilmTrailers();
        final String Trailers[][] = new String[entries.size()][2];
        for(int i =0;i<entries.size();i++)
        {
            Trailers[i][0]=entriesTrailers.get(i).getTrailer_name();
            Trailers[i][1]=entriesTrailers.get(i).getTrailer_link();

        }


      getFilmFromDataBaseToActivity();




        CompleteFilmDetails();
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

            Toast.makeText(FilmFavoriteDetials.this,"Saved to Favorites",Toast.LENGTH_LONG).show();

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
            Toast.makeText(FilmFavoriteDetials.this,"Removed from Favorites",Toast.LENGTH_LONG).show();

        }

             }

void CompleteFilmDetails()
{


    tabLayout.setupWithViewPager(viewPager);
     setTitle(Title);
    //loadDatabaseToCheckFavortieOrNot();
    setupViewPager(viewPager);


     image.setVisibility(View.VISIBLE);
     rating.setVisibility(View.VISIBLE);
     releasedate.setVisibility(View.VISIBLE);
     mLoadingIndicator.setVisibility(View.VISIBLE);
     favoriteIcon.setVisibility(View.VISIBLE);
     toolbar.setVisibility(View.VISIBLE);
     viewPager.setVisibility(View.VISIBLE);
     tabLayout.setVisibility(View.VISIBLE);

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
