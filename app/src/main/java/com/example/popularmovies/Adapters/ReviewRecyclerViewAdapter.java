package com.example.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewViewHolder> {
     private String [][] Reviews;

    public void setReviewsArr(String [][]arr){
        Reviews=arr;
    }

    public ReviewRecyclerViewAdapter(String [][]reviews) {
        Reviews = reviews;

    }


    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.reviews_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ReviewViewHolder viewHolder = new ReviewViewHolder(view);





        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.ReviewNumber.setText("["+  (position+1) +"]");
        holder.ReviewAuthor.setText(Reviews[position][0]);
        holder.ReviewLink.setText(Reviews[position][1]);
        holder.ReviewContent.setText(Reviews[position][2]);
    }


    @Override
    public int getItemCount() {
        return Reviews.length;
    }


    class ReviewViewHolder extends RecyclerView.ViewHolder  {

         TextView ReviewNumber;
         TextView ReviewAuthor;
        TextView ReviewLink;
        TextView ReviewContent;

        public ReviewViewHolder(View itemView) {
            super(itemView);

            ReviewNumber = (TextView) itemView.findViewById(R.id.review_num);
            ReviewAuthor = (TextView) itemView.findViewById(R.id.review_author);
            ReviewLink = (TextView) itemView.findViewById(R.id.review_link);
            ReviewContent = (TextView) itemView.findViewById(R.id.review_content);

         }





     }
}
