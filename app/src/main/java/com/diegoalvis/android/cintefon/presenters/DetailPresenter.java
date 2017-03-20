package com.diegoalvis.android.cintefon.presenters;

import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.diegoalvis.android.cintefon.interfaces.DetInterface;
import com.diegoalvis.android.cintefon.interfaces.DetPresenterInterface;
import com.diegoalvis.android.cintefon.models.MovieDetail;
import com.diegoalvis.android.cintefon.models.MovieItem;
import com.diegoalvis.android.cintefon.views.adapters.MovieAdapter;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

/**
 * Created by diegoalvis on 3/18/17.
 */

public class DetailPresenter  implements DetPresenterInterface {

    Context context;
    DetInterface viewInterface;

    @Override
    public void onCreate(Context context, DetInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
    }

    @Override
    public void getMovieData(int id) {
        // Get movie of DB
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        MovieItem mi = realm.where(MovieItem.class).equalTo("id", id).findFirst();
        MovieDetail movie = new MovieDetail(
                mi.getVote_count(),
                Float.parseFloat(mi.getVote_average()),
                mi.getPoster_path(),
                mi.getOverview(),
                mi.getRelease_date(),
                mi.getOriginal_title()
        );
        viewInterface.bindingData(movie);
    }

    @Override
    public void loadImageMovie(ImageView view, String poster_path) {
        try {
            Picasso.with(context).load(MovieAdapter.base_image_path + poster_path).into(view);
        } catch (Exception e) {
            Log.e("ALVIS", "Error to load image");
        }
    }


}
