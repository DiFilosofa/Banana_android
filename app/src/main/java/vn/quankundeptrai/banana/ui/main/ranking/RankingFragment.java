package vn.quankundeptrai.banana.ui.main.ranking;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 2/13/18.
 */

public class RankingFragment extends BaseFragment<RankingPresenter> implements RankingMvpView {
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_ranking;
    }

    @Override
    protected RankingPresenter onCreatePresenter() {
        return new RankingPresenter();
    }

    @Override
    protected void initialView() {

    }
}
