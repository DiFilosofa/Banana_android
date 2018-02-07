package vn.quankundeptrai.banana.ui.menuactivities.rewards;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 1/31/18.
 */

public class RewardsActivity extends BaseActivity<RewardsPresenter> implements RewardsMvpView{
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_rewards;
    }

    @Override
    protected String getScreenTitle() {
        return getString(R.string.title_rewards);
    }

    @Override
    protected RewardsPresenter onCreatePresenter() {
        return new RewardsPresenter();
    }

    @Override
    protected void initialView() {

    }
}
