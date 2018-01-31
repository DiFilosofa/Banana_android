package vn.quankundeptrai.banana.ui.menufragments.favoritelocations;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 1/31/18.
 */

public class FavoriteLocationFragment extends BaseFragment<FavoriteLocationPresenter> implements FavoriteLocationMvpView {
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_favorite_locations;
    }

    @Override
    protected FavoriteLocationPresenter onCreatePresenter() {
        return new FavoriteLocationPresenter();
    }

    @Override
    protected void initialView() {

    }
}