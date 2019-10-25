package com.example.popularmovies.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "trailer")
 public class TrailerEntry {



    @PrimaryKey(autoGenerate = true)
    private int trailer_id;
    private String film_id;
    private String trailer_name;
    private String trailer_link;


     @Ignore
     public TrailerEntry(String film_id, String trailer_name , String trailer_link ) {
         this.film_id=film_id;
         this.trailer_name=trailer_name;
         this.trailer_link=trailer_link;
      }

     public TrailerEntry(int trailer_id,String film_id, String trailer_name , String trailer_link ) {
       this.trailer_id=trailer_id;
         this.film_id=film_id;
         this.trailer_name=trailer_name;
         this.trailer_link=trailer_link;

     }

    public void setTrailer_id (int trailer_id){this.trailer_id=trailer_id;}
    public void setFilm_id(String film_id){this.film_id=film_id;}
    public void setTrailer_name(String trailer_name){this.trailer_name=trailer_name;}
    public void setTrailer_link(String trailer_link){this.trailer_link=trailer_link;}

    int getTrailer_id( ){return this.trailer_id;}
    public String getFilm_id( ){return this.film_id;}
    public String getTrailer_name(){return this.trailer_name;}
    public String getTrailer_link(){return this.trailer_link;}

}
