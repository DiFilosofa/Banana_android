package vn.quankundeptrai.banana.ui.menufragments.map;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.main.MainActivity;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 1/20/2018.
 */

public class MapFragment extends BaseFragment<MapPresenter> implements MapMvpView {
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_map;
    }

    @Override
    protected MapPresenter onCreatePresenter() {
        return new MapPresenter();
    }

    @Override
    protected void initialView() {

    }
}
