package com.example.popularmovies.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "review")
 public class ReviewEntry {



    @PrimaryKey(autoGenerate = true)
    private int review_id;
    private String film_id;
    private String review_author_name;
    private String review_link;
    private String review_content;


     @Ignore
     public ReviewEntry(String film_id, String review_author_name , String review_link , String review_content ) {
         this.film_id=film_id;
         this.review_author_name=review_author_name;
         this.review_link=review_link;
         this.review_content=review_content;
     }

     public ReviewEntry(int review_id, String film_id, String review_author_name , String review_link , String review_content ) {
        this.review_id=review_id;
         this.film_id=film_id;
         this.review_author_name=review_author_name;
         this.review_link=review_link;
         this.review_content=review_content;
    }

    public void setReview_id (int review_id){this.review_id=review_id;}
    public void setFilm_id(String film_id){this.film_id=film_id;}
    public void setReview_author_name(String review_author_name){this.review_author_name=review_author_name;}
    public void setReview_link(String review_link){this.review_link=review_link;}
    public void setReview_content(String review_content){this.review_content=review_content;}

    int getReview_id( ){return this.review_id;}
    public String getFilm_id( ){return this.film_id;}
    public String getReview_author_name(){return  this.review_author_name;}
    public String getReview_link(){return this.review_link;}
    public String getReview_content(){return this.review_content;}

}
