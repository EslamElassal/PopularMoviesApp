package com.example.popularmovies.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {FilmEntry.class,ReviewEntry.class,TrailerEntry.class},version = 1,exportSchema = false)

 public abstract class FilmDatabase extends RoomDatabase {
     public static FilmDatabase sInstance;

    public static final Object LOCK=new Object();
     public static final String DATABASE_NAME="favorite";
     public static FilmDatabase getInstance(Context context){
        if(sInstance==null)
        {}
        synchronized (LOCK){
            sInstance= Room.databaseBuilder(context.getApplicationContext(),
                    FilmDatabase.class,
                    FilmDatabase.DATABASE_NAME).build();
        }
        return sInstance;
    }

     public abstract FilmDao filmDao();
    public abstract ReviewDao reviewDao();
    public abstract TrailerDao trailerDao();

}

