package vn.quankundeptrai.banana.ui.main;

import android.location.Location;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 1/19/2018.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {
    private Disposable location;

    void waitLocation() {
        final AtomicBoolean allow = new AtomicBoolean(true);
        location = Observable.interval(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(@NonNull Long aLong) throws Exception {
                        return allow.get();
                    }
                }).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        if (CoreManager.getInstance().isLocationReady()) {
                            Location location = CoreManager.getInstance().getCurrentLocation();
                            allow.set(false);
                        }
                    }
                });
    }

    void stopLocation() {
        if (location != null && !location.isDisposed()) {
            location.dispose();
            location = null;
        }
    }

    boolean isGettingLocation() {
        return location != null;
    }
}
