package com.diegoalvis.android.cintefon.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegoalvis.android.cintefon.R;
import com.diegoalvis.android.cintefon.interfaces.MainInterface;
import com.diegoalvis.android.cintefon.models.MovieItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by diegoalvis on 3/15/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    static String base_image_path = "https://image.tmdb.org/t/p/w500";

    private List<MovieItem> movies;
    private Context context;

    MainInterface viewInterface;


    public MovieAdapter(Context context, List<MovieItem> movies, MainInterface viewInterface){
        this.movies = movies;
        this.context = context;
        this.viewInterface = viewInterface;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.ViewHolder holder, final int position) {
        final MovieItem movie = movies.get(position);

        holder.originalTitle.setText(movie.getOriginal_title());
        holder.releaseDate.setText(movie.getRelease_date());
        holder.average.setText(movie.getVote_average());
        holder.overview.setText(movie.getOverview());

        try {
            Picasso.with(context).load(base_image_path + movie.getPoster_path()).into(holder.image);
        } catch (Exception e) {
            Log.e("ALVIS", "Error to loading image");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewInterface.onMovieClick(movies.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return (movies == null) ? 0 : movies.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView originalTitle, releaseDate, average, overview;

        public ViewHolder(View v){
            super(v);
            image  = (ImageView) v.findViewById(R.id.iv_movie);
            originalTitle = (TextView) v.findViewById(R.id.tv_original_title);
            releaseDate = (TextView) v.findViewById(R.id.tv_release_date);
            average = (TextView) v.findViewById(R.id.tv_average);
            overview = (TextView) v.findViewById(R.id.tv_overview);
        }
    }

}
