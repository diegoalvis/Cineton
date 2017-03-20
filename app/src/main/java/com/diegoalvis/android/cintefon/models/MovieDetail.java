package com.diegoalvis.android.cintefon.models;

import android.databinding.BaseObservable;

/**
 * Created by diegoalvis on 3/18/17.
 */

public class MovieDetail extends BaseObservable {

    private int vote_count;
    private float vote_average;
    private String poster_path;
    private String overview;
    private String release_date;
    private String original_title;

    public MovieDetail(int vote_count, float vote_average, String poster_path, String overview, String release_date, String original_title) {
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.original_title = original_title;
    }

    public int getVote_count() {
        return vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

}
