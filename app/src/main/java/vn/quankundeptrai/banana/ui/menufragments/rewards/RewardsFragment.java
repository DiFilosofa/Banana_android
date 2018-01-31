package vn.quankundeptrai.banana.ui.menufragments.rewards;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 1/31/18.
 */

public class RewardsFragment extends BaseFragment<RewardsPresenter> implements RewardsMvpView{
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_rewards;
    }

    @Override
    protected RewardsPresenter onCreatePresenter() {
        return new RewardsPresenter();
    }

    @Override
    protected void initialView() {

    }
}
