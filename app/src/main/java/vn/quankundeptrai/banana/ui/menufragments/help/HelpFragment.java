package vn.quankundeptrai.banana.ui.menufragments.help;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 1/31/18.
 */

public class HelpFragment extends BaseFragment<HelpPresenter> implements HelpMvpView{
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_help;
    }

    @Override
    protected HelpPresenter onCreatePresenter() {
        return new HelpPresenter();
    }

    @Override
    protected void initialView() {

    }
}
