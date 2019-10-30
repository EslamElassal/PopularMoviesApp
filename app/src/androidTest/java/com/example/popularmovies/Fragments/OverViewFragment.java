package com.example.popularmovies.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.popularmovies.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class OverViewFragment extends Fragment {
    String OverViewText;

   public OverViewFragment(){}
  public OverViewFragment(String overview_text){
      OverViewText=overview_text;
  }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view =inflater.inflate(R.layout.overview_fragment, container, false);
      TextView OverView =(TextView) view.findViewById(R.id.overView_TextView);
       OverView.setText(OverViewText);
        return view;
    }

    public void setOverViewText(String text){
       OverViewText=text;
    }
}