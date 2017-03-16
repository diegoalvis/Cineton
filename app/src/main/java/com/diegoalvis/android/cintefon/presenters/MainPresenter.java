package com.diegoalvis.android.cintefon.presenters;

import android.util.Log;

import com.diegoalvis.android.cintefon.interfaces.MainInterface;
import com.diegoalvis.android.cintefon.interfaces.MainPresenterInterface;
import com.diegoalvis.android.cintefon.models.MovieItem;
import com.diegoalvis.android.cintefon.networking.connection.ApiClient;
import com.diegoalvis.android.cintefon.networking.connection.ApiInterface;
import com.diegoalvis.android.cintefon.networking.responses.MovieItemResponse;

import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by diegoalvis on 3/15/17.
 */

public class MainPresenter implements MainPresenterInterface {

    MainInterface viewInterface;

    @Override
    public void onCreate(MainInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Override
    public void getListMoviesFromService(String category) {
        makeRequest(category);
    }

    @Override
    public void showList(List<MovieItem> movies) {
        viewInterface.createList(viewInterface.createMovieAdapter(movies));
        viewInterface.showList();
    }


    //region Get Movies Data
    private void makeRequest(String category) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieItemResponse> call = apiInterface.getMovies(category);
        call.enqueue(new Callback<MovieItemResponse>() {
            @Override
            public void onResponse(Call<MovieItemResponse> call, Response<MovieItemResponse> response) {
                showList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieItemResponse> call, Throwable t) {
                Log.e("Alvis -- request", "Error in get movies list");
            }
        });
    }
    //endregion


}
