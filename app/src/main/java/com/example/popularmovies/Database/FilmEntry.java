package com.example.popularmovies.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "film")
 public class FilmEntry {



    @PrimaryKey(autoGenerate = true)
    private int favoriteid;
    private String isfavorite;
    private String id;
    private String title;
    private String image;
    private String plot;
    private String rating;
    private String releasedate;
    private String adult ;
    private String type ;
    private String backdropimage;

     @Ignore
     public FilmEntry(String id, String title , String image , String plot , String rating , String releasedate,
                      String adult, String type, String backdropimage , String isfavorite) {
         this.id=id;
         this.title = title;
         this.image = image;
         this.plot = plot;
         this.rating=rating;
         this.releasedate=releasedate;
         this.adult=adult;
         this.type=type;
         this.backdropimage=backdropimage;
         this.isfavorite=isfavorite;

     }

     public FilmEntry(int favoriteid, String id, String title , String image , String plot , String rating , String releasedate,
                      String adult, String type, String backdropimage, String isfavorite) {
         this.favoriteid=favoriteid;
         this.id=id;
         this.title = title;
         this.image = image;
         this.plot = plot;
         this.rating=rating;
         this.releasedate=releasedate;
         this.adult=adult;
         this.type=type;
         this.backdropimage=backdropimage;
         this.isfavorite=isfavorite;

    }

    public FilmEntry(int i, String title, String image, String plot, String rating, String releasedate, String adult, String type, String backdropimage, String isfavorite) {
    }

    public void setFavoriteid (int favoriteid){this.favoriteid=favoriteid;}
    public void setId(String id){this.id=id;}
    public void setTitle(String title){this.title=title;}
    public void setImage(String image){this.image=image;}
    public void setPlot(String plot){this.plot=plot;}
    public void setRating(String rating){this.rating=rating;}
    public void setRelease(String releaseDate){this.releasedate=releaseDate;}
    public void setAdult(String adult){this.adult=adult;}
    public void setType(String type){this.type=type;}
    public void setBackdropimage(String backdropimage){this.backdropimage=backdropimage;}
    public void setIsfavorite(String isfavorite){this.isfavorite=isfavorite;}

    int getFavoriteid( ){return this.favoriteid;}
    public String getId( ){return this.id;}
    public String getTitle(){return this.title;}
    public String getImage(){return this.image;}
    public String getPlot(){return this.plot;}
    public String getRating(){return this.rating;}
    public String getReleasedate(){return this.releasedate;}
    public String getAdult( ){return this.adult;}
    public String getType(){return this.type;}
    public String getBackdropimage(){return this.backdropimage;}
    public String getIsfavorite(){return this.isfavorite;}
    }
