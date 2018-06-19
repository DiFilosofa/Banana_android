package vn.quankundeptrai.banana.ui.account.login;

import vn.quankundeptrai.banana.enums.RxStatus;
import vn.quankundeptrai.banana.ui.base.BaseMvpView;

/**
 * Created by TQN on 1/27/18.
 */

public interface LoginMvpView extends BaseMvpView {
    void onLoginAttempt(RxStatus status);
}
