package com.diegoalvis.android.cintefon.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.diegoalvis.android.cintefon.R;
import com.diegoalvis.android.cintefon.interfaces.MainInterface;
import com.diegoalvis.android.cintefon.interfaces.MainPresenterInterface;
import com.diegoalvis.android.cintefon.models.MovieItem;
import com.diegoalvis.android.cintefon.presenters.MainPresenter;
import com.diegoalvis.android.cintefon.views.adapters.MovieAdapter;

import java.util.List;



public class MainActivity extends AppCompatActivity implements MainInterface {

    RecyclerView recyclerView;

    MainPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();

        presenter = new MainPresenter();
        presenter.onCreate(this, (RelativeLayout) findViewById(R.id.activity_main), this);
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
    public void showList() {

    }

    @Override
    public void onMovieClick(MovieItem movieItem) {
        //TODO: Star activity detail movie
    }


    // region get views from layout
    public void getViews() {
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setOrientation(GridLayoutManager.VERTICAL);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list_movies);
        recyclerView.setLayoutManager(glm);
    }
    // endregion
}
