package com.example.popularmovies.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmovies.Adapters.FilmsFavoritesAdapter;
import com.example.popularmovies.Database.FilmDatabase;
import com.example.popularmovies.Database.FilmEntry;
import com.example.popularmovies.Database.MainViewModel;
import com.example.popularmovies.R;

import java.util.List;

public class FavoriteFilms extends AppCompatActivity implements FilmsFavoritesAdapter.ListItemClickListener{
     private FilmDatabase mDb;
     String Id;
    private RecyclerView FilmRecyclerView;
    ProgressBar mLoadingIndicator;
    TextView EmptyListTextViewMessage;
    FilmsFavoritesAdapter filmAdapter=null;
     List<FilmEntry>Nfilms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_films);


        mDb = FilmDatabase.getInstance(getApplicationContext());

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator_FavoriteFilms);
        FilmRecyclerView=(RecyclerView)findViewById(R.id.RecyelerViewFavoriteFilms);
        EmptyListTextViewMessage=(TextView)findViewById(R.id.favorite_empty_list_textview);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //FilmRecyclerView.setLayoutManager(layoutManager);
        int numberOfColumns=2;
        FilmRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        FilmRecyclerView.setHasFixedSize(true);
setTitle("My Favorites Films");
        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
        // filmAdapter = new FilmsAdapter(Nfilms,this);
        //FilmRecyclerView.setAdapter(null);



        setupViewModel();
    }

    void setupViewModel()
    {
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);



        mainViewModel.getFilms().observe(this, new Observer<List<FilmEntry>>() {

            //
            @Override
            public void onChanged(List<FilmEntry> filmEntries) {
                filmAdapter=new FilmsFavoritesAdapter(filmEntries,FavoriteFilms.this);
                FilmRecyclerView.setAdapter(filmAdapter);
                Nfilms=filmEntries;
                if(filmEntries.size()==0)
                {
                    EmptyListTextViewMessage.setText("You don't have any favorite films");
                    EmptyListTextViewMessage.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    @Override
    public void onListItemClick(int item) {
       Intent intet = new Intent(FavoriteFilms.this,FilmFavoriteDetials.class);
       intet.putExtra("id",Nfilms.get(item).getId());
       startActivity(intet);
       finish();
    }
}
