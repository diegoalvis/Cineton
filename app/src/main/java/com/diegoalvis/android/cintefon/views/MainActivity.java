package com.diegoalvis.android.cintefon.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.diegoalvis.android.cintefon.R;
import com.diegoalvis.android.cintefon.interfaces.MainInterface;
import com.diegoalvis.android.cintefon.models.MovieItem;
import com.diegoalvis.android.cintefon.presenters.MainPresenter;
import com.diegoalvis.android.cintefon.views.adapters.MovieAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainInterface {

    RecyclerView recyclerView;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();

        presenter = new MainPresenter();
        presenter.onCreate(this);
        presenter.getListMoviesFromService("popular");
    }



    @Override
    public void createList(MovieAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public MovieAdapter createMovieAdapter(List<MovieItem> movies) {
        return new MovieAdapter(this, movies);
    }



    @Override
    public void showList() {

    }




    // region get views from layout
    public void getViews() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list_movies);
        recyclerView.setLayoutManager(llm);
    }
    // endregion
}
