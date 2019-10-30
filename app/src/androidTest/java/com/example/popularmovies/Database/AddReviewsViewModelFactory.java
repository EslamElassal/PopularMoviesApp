package com.example.popularmovies.Database;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// COMPLETED (1) Make this class extend ViewModel ViewModelProvider.NewInstanceFactory
public class AddReviewsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    // COMPLETED (2) Add two member variables. One for the database and one for the taskId
    private final FilmDatabase mDb;
    private final String mFilmId;

    // COMPLETED (3) Initialize the member variables in the constructor with the parameters received
    public AddReviewsViewModelFactory(FilmDatabase database, String filmId) {
        mDb = database;
        mFilmId = filmId;
    }

    // COMPLETED (4) Uncomment the following method
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddReviewsViewModel(mDb, mFilmId);
    }
}
