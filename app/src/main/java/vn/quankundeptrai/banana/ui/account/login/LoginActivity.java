package vn.quankundeptrai.banana.ui.account.login;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseActivity;

/**
 * Created by TQN on 1/27/18.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginMvpView {
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected String getScreenTitle() {
        return "Log In";
    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initialView() {

    }
}
