package com.example.popularmovies.Database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

// COMPLETED (5) Make this class extend ViewModel
public class AddTrailersViewModel extends ViewModel {

    // COMPLETED (6) Add a task member variable for the TaskEntry object wrapped in a LiveData
    private LiveData<List<TrailerEntry>> review;

    // COMPLETED (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public AddTrailersViewModel(FilmDatabase database, String filmId) {
        review = database.trailerDao().queryTrailersByFilmId(filmId);
    }

    // COMPLETED (7) Create a getter for the task variable
    public LiveData<List<TrailerEntry>> getTrailers() {
        return review;
    }
}
