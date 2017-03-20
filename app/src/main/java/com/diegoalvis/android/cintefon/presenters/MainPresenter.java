package com.diegoalvis.android.cintefon.presenters;

import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.diegoalvis.android.cintefon.R;
import com.diegoalvis.android.cintefon.helpers.ConnectionHelper;
import com.diegoalvis.android.cintefon.helpers.ResultsManager;
import com.diegoalvis.android.cintefon.interfaces.MainInterface;
import com.diegoalvis.android.cintefon.interfaces.MainPresenterInterface;
import com.diegoalvis.android.cintefon.interfaces.ResultManagerInterface;
import com.diegoalvis.android.cintefon.models.MovieItem;
import com.diegoalvis.android.cintefon.networking.connection.ApiClient;
import com.diegoalvis.android.cintefon.networking.connection.ApiInterface;
import com.diegoalvis.android.cintefon.networking.responses.MovieItemResponse;
import com.diegoalvis.android.cintefon.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by diegoalvis on 3/15/17.
 */

public class MainPresenter implements MainPresenterInterface, ResultManagerInterface, SearchView.OnQueryTextListener {

    String category;

    Context context;
    MainInterface viewInterface;
    ResultsManager resultsManager = ResultsManager.getInstance();


    @Override
    public void onCreate(Context context, RelativeLayout rootView, MainInterface viewInterface) {
        this.context = context;
        this.resultsManager.init(context, rootView, this);
        this.viewInterface = viewInterface;
    }

    @Override
    public void getListMoviesFromService(String category) {
        this.category = (category != null) ? category :  this.category;
        if(ConnectionHelper.isInternetAvailable(context))
            makeRequest();
        else
            loadStoredData();

    }

    public void showList(List<MovieItem> movies) {
        resultsManager.loadOk();
        viewInterface.createList(viewInterface.createMovieAdapter(movies));
    }


    @Override
    public String categorySelected(View view) {
        String option = null;
        switch (view.getId()) {
            case R.id.op_popular:
                option = context.getString(R.string.category_popular);
                break;
            case R.id.op_upcoming:
                option = context.getString(R.string.category_upcoming);
                break;
            case R.id.op_top_rated:
                option = context.getString(R.string.category_top_rated);
                break;
        }
        return option;
    }

    //region Get Movies Data
    private void makeRequest() {
        resultsManager.initLoad();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieItemResponse> call = apiInterface.getMovies(category);
        call.enqueue(new Callback<MovieItemResponse>() {
            @Override
            public void onResponse(Call<MovieItemResponse> call, Response<MovieItemResponse> response) {
                if(response.body().getResults().size() == 0) {
                    resultsManager.noItems("No movies");

                } else {
                    showList(response.body().getResults());
                    storeData(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieItemResponse> call, Throwable t) {
                resultsManager.errorLoad("", "There was a problem loading movies", "RELOAD");
                Log.e("Alvis -- request", "Error getting movies list");
            }
        });
    }
    //endregion

    // region Data Base access write and read
    private void storeData(List<MovieItem> results) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (int i = 0; i < results.size(); i++) {
            results.get(i).setId(i);
            results.get(i).setCategory(category);
        }
        realm.copyToRealmOrUpdate(results);
        realm.commitTransaction();
    }

    private void loadStoredData() {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        List<MovieItem> results = realm.where(MovieItem.class).equalTo("category", category).findAll();
        if (results.size() == 0)
            resultsManager.errorLoad("", "No internet access", "RETRY");
        else
            showList(results);
    }
    // endregion


    @Override
    public void actionSnack() {
        makeRequest();
    }

    // region search view
    @Override
    public boolean setMenu(Menu menu) {
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint(context.getResources().getString(R.string.hint_search));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        viewInterface.searchMovies(newText);
        return false;
    }
    // endregion


}
