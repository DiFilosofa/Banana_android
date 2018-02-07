package vn.quankundeptrai.banana.ui.menuactivities.feedback;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.menuactivities.help.HelpPresenter;

/**
 * Created by TQN on 1/31/18.
 */

public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements FeedbackMvpView {
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_feedback;
    }

    @Override
    protected String getScreenTitle() {
        return getString(R.string.title_feedback);
    }

    @Override
    protected FeedbackPresenter onCreatePresenter() {
        return new FeedbackPresenter();
    }

    @Override
    protected void initialView() {

    }
}
