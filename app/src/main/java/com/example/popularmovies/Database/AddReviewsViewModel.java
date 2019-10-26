package com.example.popularmovies.Database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

// COMPLETED (5) Make this class extend ViewModel
public class AddReviewsViewModel extends ViewModel {

    // COMPLETED (6) Add a task member variable for the TaskEntry object wrapped in a LiveData
    private LiveData<List<ReviewEntry>> review;

    // COMPLETED (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public AddReviewsViewModel(FilmDatabase database, String filmId) {
        review = database.reviewDao().queryReviewsByFilmId(filmId);
    }

    // COMPLETED (7) Create a getetter for the task variable
    public LiveData<List<ReviewEntry>> getReviews() {
        return review;
    }
}
