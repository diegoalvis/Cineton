package com.diegoalvis.android.cintefon.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.diegoalvis.android.cintefon.R;
import com.diegoalvis.android.cintefon.databinding.ActivityDetailBinding;
import com.diegoalvis.android.cintefon.interfaces.DetInterface;
import com.diegoalvis.android.cintefon.interfaces.DetPresenterInterface;
import com.diegoalvis.android.cintefon.models.MovieDetail;
import com.diegoalvis.android.cintefon.presenters.DetailPresenter;

public class DetailActivity extends AppCompatActivity implements DetInterface {

    DetPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        presenter = new DetailPresenter();
        presenter.onCreate(this, this);
        presenter.getMovieData(getIntent().getIntExtra("id", 0));
    }

    @Override
    public void bindingData(MovieDetail movieDetail) {
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setMovie(movieDetail);
        presenter.loadImageMovie((ImageView) findViewById(R.id.iv_movie_detail), movieDetail.getPoster_path());

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


}
