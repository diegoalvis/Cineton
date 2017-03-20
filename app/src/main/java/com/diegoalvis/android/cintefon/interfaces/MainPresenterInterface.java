package com.diegoalvis.android.cintefon.interfaces;


import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import com.diegoalvis.android.cintefon.models.MovieItem;

import java.util.List;

public interface MainPresenterInterface {

    void onCreate(Context context, RelativeLayout rootView, MainInterface viewInterface);
    void getListMoviesFromService(String category);
    void showList(List<MovieItem> movies);
    boolean setMenu(Menu menu);
    String categorySelected(View view);
    //void onItemClicked(int position);

}
