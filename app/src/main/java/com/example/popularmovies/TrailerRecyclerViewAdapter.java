package com.example.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class TrailerRecyclerViewAdapter extends RecyclerView.Adapter<TrailerRecyclerViewAdapter.TrailerViewHolder> {
     private String [][] Trailers;

    public void setTrailersArr(String [][]arr){
        Trailers=arr;
    }
Context mContext;
LifecycleOwner mLifecycle;


    public TrailerRecyclerViewAdapter(String [][]trailers,LifecycleOwner lifecycle,Context context) {
        Trailers = trailers;
        mLifecycle=lifecycle;
        mContext=context;
    }


    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.trailers_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        TrailerViewHolder viewHolder = new TrailerViewHolder(view);





        return viewHolder;
    }


    @Override
    public void onBindViewHolder(TrailerViewHolder holder,final int position) {
        holder.TrailerNumber.setText("["+  (position+1) +"]");
        holder.TrailerName.setText(Trailers[position][0]);
        mLifecycle.getLifecycle().addObserver(holder.TrailerVideo);
        holder.TrailerVideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = Trailers[position][1];
                youTubePlayer.loadVideo(videoId, 0);
                youTubePlayer.pause();
            }
        });

        holder.YoutubeExternalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Video_Id = Trailers[position][1];
               mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://"+Video_Id)));
            }
        });


    }


    @Override
    public int getItemCount() {
        return Trailers.length;
    }


    class TrailerViewHolder extends RecyclerView.ViewHolder  {

         TextView TrailerNumber;
         TextView TrailerName;
         YouTubePlayerView TrailerVideo;
         Button YoutubeExternalButton;
        public TrailerViewHolder(View itemView) {
            super(itemView);

            TrailerNumber = (TextView) itemView.findViewById(R.id.trailer_num);
            TrailerName = (TextView) itemView.findViewById(R.id.trailer_name);
            TrailerVideo = (YouTubePlayerView) itemView.findViewById(R.id.trailers_content_youtube_view);
            YoutubeExternalButton = (Button)itemView.findViewById(R.id.Youtube_external_app_button);


        }





     }
}
