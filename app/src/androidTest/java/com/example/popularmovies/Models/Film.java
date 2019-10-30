package com.example.popularmovies.Models;

import androidx.recyclerview.widget.RecyclerView;

public class Film {
  //Empty Constructor
   public  Film ()
  {

  }
    //Basic Film Elements
    String Id;
    String Title;
    String Image;
    String Plot;
    String Rating;
    String ReleaseDate;
    //Detialed Film Data Elements

    String ADULT ;
    String TYPE ;
    String [][]REVIEWS;
    String [][]TRAILERS;
    String BACKDROP_IMAGE;
    String IsFavorite;
//This Constructor To Fetch Basic Film Data From Network In MainActivity
public  Film(String id,String title , String image , String plot , String rating , String releaseDate,String isFavorite)
    {
        Id=id;
        Title=title;
        Image = image;
        Plot = plot;
        Rating=rating;
        ReleaseDate=releaseDate;
        IsFavorite=isFavorite;
    }

//this Constructor To Get Detials Data In FilmDetials Activity
public Film(String adult,String type,String BackDrop_Image)
    {

        ADULT=adult;
        TYPE=type;
        BACKDROP_IMAGE=BackDrop_Image;
    }

    public void setId(String id){Id=id;}
    public void setTitle(String title){Title=title;}
    public void setImage(String image){Image=image;}
    public void setPlot(String plot){Plot=plot;}
    public void setRating(String rating){Rating=rating;}
    public void setReleaseDate(String releaseDate){ReleaseDate=releaseDate;}
    public void setAdult(String adult){ADULT=adult;}
    public void setType(String type){TYPE=type;}
    public void setBackDrop_Image(String backdrop_image){BACKDROP_IMAGE=backdrop_image;}
    public void setReviews(String [][]reviews){REVIEWS=reviews;}
    public void setTrailers(String [][]trailers){TRAILERS=trailers;}
    public void setIsFavorite(String isFavorite){this.IsFavorite=isFavorite;}

    public String getId( ){return Id;}
    public String getTitle(){return Title;}
    public String getImage(){return Image;}
    public String getPlot(){return Plot;}
    public String getRating(){return Rating;}
    public String getReleaseDate(){return ReleaseDate;}
    public String getAdult( ){return ADULT;}
    public String getType(){return TYPE;}
    public String getBackDrop_Image(){return BACKDROP_IMAGE;}
    public String [][]getReviews( ){return REVIEWS;}
    public String[][] getTrailers(){return TRAILERS;}
    public String getIsFavorite(){return IsFavorite;}

}
