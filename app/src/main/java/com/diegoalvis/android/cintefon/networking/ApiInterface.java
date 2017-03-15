package com.diegoalvis.android.cintefon.networking;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by diegoalvis on 03/14/17.
 * Interface where define all
 * request with their repective
 * fields for the application
 */

public interface ApiInterface {

    String popular_path = "/movie/popular?api_key=e438287d6167b07734b6e68210e081ab&language=en-US&page=1";
    String top_rated_path = "/movie/top_rated?api_key=e438287d6167b07734b6e68210e081ab&language=en-US&page=1";
    String upcoming_path = "/movie/upcoming?api_key=e438287d6167b07734b6e68210e081ab&language=en-US&page=1";

    @Headers({"Content-Type: application/json"})
    @GET(popular_path)
    Call<JsonObject> getPopularMovies();

    @Headers({"Content-Type: application/json"})
    @GET(top_rated_path)
    Call<JsonObject> getTopRatedMovies();

    @Headers({"Content-Type: application/json"})
    @GET(upcoming_path)
    Call<JsonObject> getUpcomingMovies();

}
