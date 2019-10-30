package com.example.popularmovies.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TrailerDao {

    @Query("SELECT * FROM trailer where film_id = :id  order by trailer_id")
    LiveData<List<TrailerEntry>> queryTrailersByFilmId(String id);

      @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTrailer(TrailerEntry trailerEntry);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTrailers(List<TrailerEntry> trailerEntries);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTrailer(TrailerEntry trailerEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTrailers(List<TrailerEntry> trailerEntries);

    @Query("DELETE FROM trailer WHERE film_id = :id")
    void delelteTrailersByFilmId(String id);




}
