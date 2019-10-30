package com.example.popularmovies.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FilmDao {

    @Query("SELECT * FROM film order by favoriteid")
    LiveData<List<FilmEntry>> queryFilm();
      @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilm(FilmEntry filmEntry);
     @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFilm(FilmEntry filmEntry);

    @Query("DELETE FROM film WHERE id = :id")
    void delelteFilm(String id);

    @Query("SELECT * FROM film where id = :id")
    LiveData<FilmEntry> queryFilmById(String id);


}
