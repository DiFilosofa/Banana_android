package vn.quankundeptrai.banana.ui.menuactivities.favoritelocations;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseActivity;

/**
 * Created by TQN on 1/31/18.
 */

public class FavoriteLocationActivity extends BaseActivity<FavoriteLocationPresenter> implements FavoriteLocationMvpView {

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

    }
}