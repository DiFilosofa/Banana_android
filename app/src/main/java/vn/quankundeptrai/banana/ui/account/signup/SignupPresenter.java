package vn.quankundeptrai.banana.ui.account.signup;

import vn.quankundeptrai.banana.data.ApiObservable;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.enums.RxStatus;
import vn.quankundeptrai.banana.interfaces.IErrorTask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 1/27/18.
 */

public class SignupPresenter extends BasePresenter<SignupMvpView> {
    void signup(String email, String password, String confirmPassword, String nickname, String address, String phone) {
        callApi(ApiObservable.signUpAndLogin(email, password, confirmPassword, nickname, address, phone), new IErrorTask<RxStatus>() {
            @Override
            public boolean onError() {
                CoreManager.getInstance().logout();
                return false;
            }

            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(RxStatus result) {
                getMvpView().onRegisterAttempt(result);
            }

            @Override
            public void onPostTask() {

            }
        });
    }
}
