package com.diegoalvis.android.cintefon.networking;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by diegoalvis on 1/23/17.
 * Interface where define all
 * request with their repective
 * fields for the application
 */

public interface ApiInterface {


    @Headers({"Content-Type: application/json"})
    @GET("/us/rss/topfreeapplications/limit=20/json")
    Call<JsonObject> getApplications();

}
