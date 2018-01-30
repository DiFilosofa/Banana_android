package vn.quankundeptrai.banana.ui.account.login;

import vn.quankundeptrai.banana.data.ApiObservable;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.enums.RxStatus;
import vn.quankundeptrai.banana.interfaces.IErrorTask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 1/27/18.
 */

public class LoginPresenter extends BasePresenter<LoginMvpView> {
    void login(String email, String password){
        callApi(ApiObservable.login(email, password), new IErrorTask<RxStatus>() {
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
                if(result != RxStatus.Success){
                    CoreManager.getInstance().logout();
                }
                getMvpView().onLoginAttempt(result);
            }

            @Override
            public void onPostTask() {

            }
        });
    }
}
