package com.example.popularmovies.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReviewDao {

    @Query("SELECT * FROM review where film_id = :id  order by review_id")
    LiveData<List<ReviewEntry>> queryReviewsByFilmId(String id);

      @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReview(ReviewEntry reviewEntry);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReviews(List<ReviewEntry> reviewEntries);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateReview(ReviewEntry reviewEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateReviews(List<ReviewEntry> reviewEntries);

    @Query("DELETE FROM review WHERE film_id = :id")
    void delelteReviewsByFilmId(String id);




}
