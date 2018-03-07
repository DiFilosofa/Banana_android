package vn.quankundeptrai.banana.ui.menu.favoritelocations;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.models.favoriteLocation.FavoriteLocationItemModel;
import vn.quankundeptrai.banana.interfaces.IFavoriteLocationCallback;
import vn.quankundeptrai.banana.ui.adapter.FavoriteLocationsAdapter;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.utils.InstantiateUtils;

/**
 * Created by TQN on 1/31/18.
 */

public class FavoriteLocationActivity extends BaseActivity<FavoriteLocationPresenter> implements FavoriteLocationMvpView, IFavoriteLocationCallback {
    private FavoriteLocationsAdapter adapter ;
    private List<FavoriteLocationItemModel> list;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_favorite_locations;
    }

    @Override
    protected String getScreenTitle() {
        return getString(R.string.title_fav_location);
    }

    @Override
    protected FavoriteLocationPresenter onCreatePresenter() {
        return new FavoriteLocationPresenter();
    }

    @Override
    protected void initialView() {
        adapter = new FavoriteLocationsAdapter(this, list = InstantiateUtils.generateFavoriteLocations(this), this);
        RecyclerView favoriteRecycler = mainView.findViewById(R.id.favoriteRecycler);
        favoriteRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        favoriteRecycler.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, boolean isChecked) {
//        updateFavorite(position, isChecked);
    }
}