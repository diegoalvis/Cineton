package com.diegoalvis.android.cintefon.interfaces;


import com.diegoalvis.android.cintefon.models.MovieItem;

import java.util.List;

public interface MainPresenterInterface {

    void onCreate(MainInterface viewInterface);  // reference to view
    void getListMoviesFromService(String category);
    void showList(List<MovieItem> movies);
    //void onItemClicked(int position);

}
