package com.example.popularmovies;

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
    Film(String id,String title , String image , String plot , String rating , String releaseDate,String isFavorite)
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
    Film(String adult,String type,String BackDrop_Image)
    {

        ADULT=adult;
        TYPE=type;
        BACKDROP_IMAGE=BackDrop_Image;
    }

    void setId(String id){Id=id;}
    void setTitle(String title){Title=title;}
    void setImage(String image){Image=image;}
    void setPlot(String plot){Plot=plot;}
    void setRating(String rating){Rating=rating;}
    void setReleaseDate(String releaseDate){ReleaseDate=releaseDate;}
    void setAdult(String adult){ADULT=adult;}
    void setType(String type){TYPE=type;}
    void setBackDrop_Image(String backdrop_image){BACKDROP_IMAGE=backdrop_image;}
    void setReviews(String [][]reviews){REVIEWS=reviews;}
    void setTrailers(String [][]trailers){TRAILERS=trailers;}
    void setIsFavorite(String isFavorite){this.IsFavorite=isFavorite;}

    String getId( ){return Id;}
    String getTitle(){return Title;}
    String getImage(){return Image;}
    String getPlot(){return Plot;}
    String getRating(){return Rating;}
    String getReleaseDate(){return ReleaseDate;}
    String getAdult( ){return ADULT;}
    String getType(){return TYPE;}
    String getBackDrop_Image(){return BACKDROP_IMAGE;}
    String [][]getReviews( ){return REVIEWS;}
    String[][] getTrailers(){return TRAILERS;}
    String getIsFavorite(){return IsFavorite;}

}
