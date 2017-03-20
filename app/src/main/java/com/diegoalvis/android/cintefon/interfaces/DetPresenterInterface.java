package com.diegoalvis.android.cintefon.interfaces;


import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.diegoalvis.android.cintefon.models.MovieDetail;
import com.diegoalvis.android.cintefon.models.MovieItem;

import java.util.List;

public interface DetPresenterInterface {

    void onCreate(Context context, DetInterface viewInterface);
    void getMovieData(int id);
    void loadImageMovie(ImageView view, String poster_path);
}
