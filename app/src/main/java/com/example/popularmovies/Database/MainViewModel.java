package com.example.popularmovies.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<FilmEntry>> films;
  //  private LiveData<List<ReviewEntry>> reviews;
    //private LiveData<List<TrailerEntry>> trailers;

    public MainViewModel(@NonNull Application application) {
        super(application);
        FilmDatabase database= FilmDatabase.getInstance(this.getApplication());
        films=database.filmDao().queryFilm();
      //   reviews=database.reviewDao().queryReviewsByFilmId();
        // trailers=database.trailerDao().queryTrailersByFilmId();
    }

    public LiveData<List<FilmEntry>> getFilms() {
        return films;
    }
}