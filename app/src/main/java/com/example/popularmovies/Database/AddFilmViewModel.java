package com.example.popularmovies.Database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

// COMPLETED (5) Make this class extend ViewModel
public class AddFilmViewModel extends ViewModel {

    // COMPLETED (6) Add a task member variable for the TaskEntry object wrapped in a LiveData
    private LiveData<FilmEntry> film;

    // COMPLETED (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public AddFilmViewModel(FilmDatabase database, String filmId) {
        film = database.filmDao().queryFilmById(filmId);
    }

    // COMPLETED (7) Create a getter for the task variable
    public LiveData<FilmEntry> getFilm() {
        return film;
    }
}
