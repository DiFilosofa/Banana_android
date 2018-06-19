package vn.quankundeptrai.banana.customviews.general;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 1/30/18.
 */

public class LoadingView extends RelativeLayout {
    private View mainView;
    private AtomicBoolean isLoading;
    private RelativeLayout loading;
    private Context context;
    private TextView error;
    private boolean showError = true;

    public LoadingView(Context context) {
        super(context);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        this.context = context;
        isLoading = new AtomicBoolean(false);
        post(new Runnable() {
            @Override
            public void run() {
                if (getChildCount() > 0) {
                    mainView = getChildAt(0);
                    mainView.setVisibility(GONE);
                    loading = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
                    loading.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                    View bar = loading.findViewById(R.id.bar);
                    bar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotation));
                    addView(loading);
                    loadComplete();
                }
            }
        });
    }

    public void loadComplete() {
        if (isLoading.get()) {
            loadDone();
        }
        isLoading.set(true);
        removeError();
    }

    public void error(String err) {
        loadComplete();
        mainView.setVisibility(GONE);
        removeError();
        error = new TextView(context);
        error.setText(err);
        error.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        error.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        error.setGravity(Gravity.CENTER);

        addView(error);
    }

    private void removeError() {
        if (error != null) {
            removeView(error);
            error = null;
        }
    }

    private void loadDone() {
        mainView.setVisibility(VISIBLE);
        loading.setVisibility(GONE);
        showError = false;
    }

    public void reset() {
        if (mainView != null && loading != null) {
            mainView.setVisibility(GONE);
            loading.setVisibility(VISIBLE);
            removeError();
        }
    }

    public void waitForTimeOut(BasePresenter presenter) {
        removeError();
        presenter.addDisposable(Completable.timer(AppConstants.API_TIMEOUT, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (showError) {
                            error(context.getString(R.string.session_timeout));
                        }
                    }
                }));
    }
}
