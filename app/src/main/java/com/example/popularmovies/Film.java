package com.example.popularmovies;

import androidx.recyclerview.widget.RecyclerView;

public class Film {
    String Title;
    String Image;
    String Plot;
    String Rating;
    String ReleaseDate;

    Film(String title , String image , String plot , String rating , String releaseDate)
    {
        Title=title;
        Image = image;
        Plot = plot;
        Rating=rating;
        ReleaseDate=releaseDate;
    }
    void setTitle(String title){Title=title;}
    void setImage(String image){Image=image;}
    void setPlot(String plot){Plot=plot;}
    void setRating(String rating){Rating=rating;}
    void setReleaseDate(String releaseDate){ReleaseDate=releaseDate;}

    String getTitle(){return Title;}
    String getImage(){return Image;}
    String getPlot(){return Plot;}
    String getRating(){return Rating;}
    String getReleaseDate(){return ReleaseDate;}

}
