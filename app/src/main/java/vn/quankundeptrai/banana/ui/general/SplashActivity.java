package vn.quankundeptrai.banana.ui.general;

import android.content.Intent;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.ui.account.login.LoginActivity;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 1/27/18.
 */

public class SplashActivity extends BaseActivity {
    private Observer observer = new Observer() {
        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object o) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initialView() {

    }

    @Override
    protected void onResume() {
        Observable.timer(AppConstants.SPLASH_DELAY, TimeUnit.SECONDS).subscribe(observer);
        super.onResume();
    }
}
