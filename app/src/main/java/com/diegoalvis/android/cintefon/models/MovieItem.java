package com.diegoalvis.android.cintefon.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by diegoalvis on 3/14/17.
 */

public class MovieItem extends RealmObject{

    @PrimaryKey
    private int id;

    private boolean adult;
    private double  popularity;
    private int vote_count;
    private int[] genre_ids;
    private String poster_path;
    private String overview;
    private String release_date;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private String vote_average;

    public MovieItem() {
    }

}
