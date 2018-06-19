package vn.quankundeptrai.banana.ui.main;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import vn.quankundeptrai.banana.data.ApiObservable;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.data.models.responses.BaseResponse;
import vn.quankundeptrai.banana.enums.RxStatus;
import vn.quankundeptrai.banana.interfaces.ITask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 1/19/2018.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {
    void getAllEvents() {
        callBaseApi(ApiObservable.getAllEvents(), new ITask<ArrayList<Event>>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(ArrayList<Event> result) {
                getMvpView().onGetEventsSuccess(result);
            }

            @Override
            public void onPostTask() {

            }
        });
    }

    void getProfile() {
        callApi(ApiObservable.getProfile(), new ITask<RxStatus>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(RxStatus result) {
                getMvpView().onGetProfileDone(result == RxStatus.Success);
            }

            @Override
            public void onPostTask() {

            }
        });
    }
}
