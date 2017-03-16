package com.diegoalvis.android.cintefon.networking.responses;

import com.diegoalvis.android.cintefon.models.MovieItem;

import java.util.List;

/**
 * Created by diegoalvis on 3/15/17.
 */

public class MovieItemResponse {

    int page;
    List<MovieItem> results;
    int total_results;
    int total_pages;


    public MovieItemResponse() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieItem> getResults() {
        return results;
    }

    public void setResults(List<MovieItem> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
