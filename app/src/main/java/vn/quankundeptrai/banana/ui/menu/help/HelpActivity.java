package vn.quankundeptrai.banana.ui.menu.help;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseActivity;

/**
 * Created by TQN on 1/31/18.
 */

public class HelpActivity extends BaseActivity<HelpPresenter> implements HelpMvpView{
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_help;
    }

    @Override
    protected String getScreenTitle() {
        return getString(R.string.title_help);
    }

    @Override
    protected HelpPresenter onCreatePresenter() {
        return new HelpPresenter();
    }

    @Override
    protected void initialView() {

    }
}
