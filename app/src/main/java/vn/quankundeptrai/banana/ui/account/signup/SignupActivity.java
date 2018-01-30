package vn.quankundeptrai.banana.ui.account.signup;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseActivity;

/**
 * Created by TQN on 1/27/18.
 */

public class SignupActivity extends BaseActivity<SignupPresenter> implements SignupMvpView {
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected SignupPresenter onCreatePresenter() {
        return new SignupPresenter();
    }

    @Override
    protected void initialView() {

    }
}
