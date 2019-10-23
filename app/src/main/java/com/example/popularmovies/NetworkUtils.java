package com.example.popularmovies;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the weather servers.
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String POPULAR_FILMS_URL =
            "" +
                    "";

    private static final String TOP_RATED_FILMS_URL =
            "http://api.themoviedb.org/3/movie/top_rated?api_key=b9ef75ce7022c8417b4335cb4551ad86";
    private static final String FILM_REVIEWS ="http://api.themoviedb.org/3/movie/475557/reviews?api_key=b9ef75ce7022c8417b4335cb4551ad86";
    private static final String FILM_TRAILERS ="http://api.themoviedb.org/3/movie/475557/trailers?api_key=b9ef75ce7022c8417b4335cb4551ad86";

    /*
     * NOTE: These values only effect responses from OpenWeatherMap, NOT from the fake weather
     * server. They are simply here to allow us to teach you how to build a URL if you were to use
     * a real API.If you want to connect your app to OpenWeatherMap's API, feel free to! However,
     * we are not going to show you how to do so in this course.
     */

    /* The format we want our API to return */


    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
      * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String URL_QUERY) {
        // COMPLETED (1) Fix this method to return the URL used to query Open Weather Map's API
        Uri builtUri = Uri.parse(URL_QUERY).buildUpon().build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return url;
    }



    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}