package vn.quankundeptrai.banana.ui.base;

/**
 * Created by TQN on 1/19/2018.
 */

public interface Presenter<V extends BaseMvpView> {

    void attachView(V mvpView);

    void detachView();
}
