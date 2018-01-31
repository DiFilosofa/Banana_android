package vn.quankundeptrai.banana.ui.menufragments.feedback;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseFragment;
import vn.quankundeptrai.banana.ui.menufragments.help.HelpMvpView;
import vn.quankundeptrai.banana.ui.menufragments.help.HelpPresenter;

/**
 * Created by TQN on 1/31/18.
 */

public class FeedbackFragment extends BaseFragment<HelpPresenter> implements HelpMvpView {
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_feedback;
    }

    @Override
    protected HelpPresenter onCreatePresenter() {
        return new HelpPresenter();
    }

    @Override
    protected void initialView() {

    }
}
