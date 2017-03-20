package com.diegoalvis.android.cintefon.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import com.diegoalvis.android.cintefon.R;
import com.diegoalvis.android.cintefon.interfaces.MainInterface;
import com.diegoalvis.android.cintefon.interfaces.MainPresenterInterface;
import com.diegoalvis.android.cintefon.models.MovieItem;
import com.diegoalvis.android.cintefon.presenters.MainPresenter;
import com.diegoalvis.android.cintefon.views.adapters.MovieAdapter;

import java.util.List;



public class MainActivity extends AppCompatActivity implements MainInterface, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    MainPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();

        presenter = new MainPresenter();
        presenter.onCreate(this, (RelativeLayout) findViewById(R.id.activity_main), this);
        optionClickCategory(findViewById(R.id.op_popular));
    }

    public void optionClickCategory(View view) {
        presenter.getListMoviesFromService(presenter.categorySelected(view));
    }


    @Override
    public MovieAdapter createMovieAdapter(List<MovieItem> movies) {
        return new MovieAdapter(this, movies, this);
    }

    @Override
    public void createList(MovieAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onMovieClick(MovieItem movieItem) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", movieItem.getId());
        startActivity(intent);
    }


    @Override
    public void searchMovies(String keyWord) {
        ((MovieAdapter)recyclerView.getAdapter()).searchResults(keyWord);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return presenter.setMenu(menu);
    }

    // region get views from layout
    public void getViews() {
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list_movies);
        recyclerView.setLayoutManager(glm);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_main);
        swipeRefresh.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        presenter.getListMoviesFromService(null);
        swipeRefresh.setRefreshing(false);
    }
    // endregion
}
