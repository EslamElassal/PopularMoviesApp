package com.example.popularmovies.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.Database.FilmEntry;
import com.example.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmsFavoritesAdapter extends RecyclerView.Adapter<FilmsFavoritesAdapter.FilmViewHolder> {
  List<FilmEntry> Nfilms ;
Context myContext;
     final private ListItemClickListener mOnClickListener;


     public interface ListItemClickListener{
        void onListItemClick(int item);
    }


    public FilmsFavoritesAdapter(List<FilmEntry> films, ListItemClickListener listItemClickListener) {
        Nfilms = films;
        mOnClickListener= listItemClickListener;
     }

    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        myContext=context;
        int layoutIdForListItem = R.layout.film_favorite_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        FilmViewHolder viewHolder = new FilmViewHolder(view);


         return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        holder.bind(position);

    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available
     */
    @Override
    public int getItemCount() {
        return Nfilms.size();
    }

    // TODO (5) Implement OnClickListener in the NumberViewHolder class

    /**
     * Cache of the children views for a list item.
     */
    class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        ImageView FilmImage;
        TextView filmName,filmRate;
        // Will display which ViewHolder is displaying this data

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
          */
        public FilmViewHolder(View itemView) {
            super(itemView);

            FilmImage = (ImageView) itemView.findViewById(R.id.film_favorite_main_image);
             filmName=(TextView)itemView.findViewById(R.id.film_favorite_name_main);
             filmRate=(TextView)itemView.findViewById(R.id.film_favorite_rate_main);

            itemView.setOnClickListener(this);
            // TODO (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
          */
        void bind(int filmIndex  ) {
            FilmEntry entity = Nfilms.get(filmIndex);
              Picasso.with(myContext)
                      .load(entity.getImage())
                   .into(FilmImage);
            filmName.setText(entity.getTitle());
             filmRate.setText(entity.getRating());
            //Nfilms[filmIndex].getTitle();

        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }

        // TODO (6) Override onClick, passing the clicked item's position (getAdapterPosition()) to mOnClickListener via its onListItemClick method
    }
    public void setFilmsData( List<FilmEntry>  filmsData) {

           Nfilms = filmsData;


        notifyDataSetChanged();
    }
    public List<FilmEntry> getFilmsData() {
        return Nfilms;
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */

}
