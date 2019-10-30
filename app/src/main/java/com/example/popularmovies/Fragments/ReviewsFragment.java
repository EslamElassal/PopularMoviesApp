package com.example.popularmovies.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.Adapters.ReviewRecyclerViewAdapter;
import com.example.popularmovies.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReviewsFragment extends Fragment {
    String [][]ReviewsArr;
    ReviewRecyclerViewAdapter mAdapter;
    public ReviewsFragment(){}
    public ReviewsFragment(String reviews_arr[][]){
        ReviewsArr=reviews_arr;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reviews_fragment, container, false);
       RecyclerView ReviewList= view.findViewById(R.id.reviews_recycler_View);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        ReviewList.setLayoutManager(layoutManager);

        ReviewList.setHasFixedSize(true);
        mAdapter = new ReviewRecyclerViewAdapter(ReviewsArr);
        ReviewList.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    public void setReviewsArr(String [][]arr){
       ReviewsArr=arr;
    }
}