package com.example.popularmovies;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public final class GetFilmsJsonData {



    public static Film[] getSimpleFilmStringsFromJson(Context context, String forecastJsonStr)
            throws JSONException {

        final String TITLE   = "title";
        final String IMAGE = "poster_path";
        final String RATING = "vote_average";
        final String RELEASE_DATE = "release_date";
        final String PLOT = "overview";


        JSONObject jsonObject = new JSONObject(forecastJsonStr);

        JSONArray results = jsonObject.getJSONArray("results");
        Film[] parseFilmData = new Film[results.length()];

        for(int i = 0; i < results .length(); i++)
        {
            JSONObject film = results.getJSONObject(i);

            String image =film.getString (IMAGE);
            image="http://image.tmdb.org/t/p/w780"+image;
            String title =film.getString(TITLE);
            String rating =film.getString(RATING);
            String plot =film.getString(PLOT);
            String release_date =film.getString(RELEASE_DATE);

            parseFilmData[i]=new Film(title,image,plot,rating,release_date);


        }


        return parseFilmData;
    }


}
