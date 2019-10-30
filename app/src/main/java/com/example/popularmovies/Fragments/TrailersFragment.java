package com.example.popularmovies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TrailersFragment extends Fragment{

    public TrailerRecyclerViewAdapter mAdapter;
    String [][]TrailersArr;
    public TrailersFragment(){}
        public TrailersFragment(String trailers_arr[][]){
            TrailersArr=trailers_arr;
        }


    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.trailers_fragment, container, false);
            RecyclerView TrailerList= view.findViewById(R.id.trailers_recycler_View);


            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            TrailerList.setLayoutManager(layoutManager);

            TrailerList.setHasFixedSize(true);
            mAdapter = new TrailerRecyclerViewAdapter(TrailersArr,getViewLifecycleOwner(),getContext());
            TrailerList.setAdapter(mAdapter);

            // Inflate the layout for this fragment
            return view;

        }
public void setTrailersArr(String [][]arr){
       TrailersArr=arr;
}
}
