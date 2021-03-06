package com.diegoalvis.android.cintefon.interfaces;

import com.diegoalvis.android.cintefon.models.MovieItem;
import com.diegoalvis.android.cintefon.views.adapters.MovieAdapter;

import java.util.List;


public interface MainInterface {

    void createList(MovieAdapter adapter);
    MovieAdapter createMovieAdapter(List<MovieItem> movies);
    void onMovieClick(MovieItem movieItem);
    void searchMovies(String keyWord);
}
