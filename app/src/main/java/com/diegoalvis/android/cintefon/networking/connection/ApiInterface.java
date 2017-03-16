package com.diegoalvis.android.cintefon.networking.connection;

import com.diegoalvis.android.cintefon.networking.responses.MovieItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


/**
 * Created by diegoalvis on 03/14/17.
 * Interface where define all
 * request with their repective
 * fields for the application
 */

public interface ApiInterface {

    @Headers({"Content-Type: application/json"})
    @GET("movie/{category}?api_key=e438287d6167b07734b6e68210e081ab&language=en-US&page=1")
    Call<MovieItemResponse> getMovies(@Path("category") String category);

}
