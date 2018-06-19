package vn.quankundeptrai.banana.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.quankundeptrai.banana.data.CoreManager;

/**
 * Created by TQN on 1/19/2018.
 */

public abstract class BaseFragment <T extends BasePresenter> extends Fragment {
    protected View mainView;
    private T presenter;

    public BaseFragment() {
        // Required empty public constructor
    }

    protected abstract int getLayoutResource();

    protected abstract T onCreatePresenter();

    protected abstract void initialView();

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        mainView = inflater.inflate(getLayoutResource(), container, false);
        if (getPresenter() != null) {
            getPresenter().attachView((BaseMvpView) this);
        }

        initialView();

        return mainView;
    }

    protected T getPresenter() {
        if (presenter == null) {
            presenter = onCreatePresenter();
        }

        return presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getPresenter() != null) {
            getPresenter().detachView();
            presenter = null;
        }
    }

    protected BaseActivity getCurrentActivity() {
        BaseActivity activity = (BaseActivity) getActivity();
        return activity == null ? CoreManager.getInstance().getCurrentActivity() : activity;
    }

    protected String getResString(int resId) {
        return getCurrentActivity().getResources().getString(resId);
    }


}
