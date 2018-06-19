package vn.quankundeptrai.banana.ui.main.map;

import android.location.Location;
import android.util.Log;
import android.util.Pair;

import com.google.android.gms.maps.model.LatLng;

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
import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleDirectionResponse;
import vn.quankundeptrai.banana.interfaces.ITask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 2/13/18.
 */

public class MapPresenter extends BasePresenter<MapMvpView> {
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

    void getRoute(Pair<LatLng, LatLng> eventLocations, final Integer color) {
        Log.e("aaaa",eventLocations.first.latitude + "-" + eventLocations.first.longitude);
        Log.e("aaaab",eventLocations.second.latitude + "-" + eventLocations.second.longitude);

        callApi(ApiObservable.getEventPolyline(mContext, eventLocations), new ITask<GoogleDirectionResponse>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(GoogleDirectionResponse result) {
                if (result.getRoutes().isEmpty()) {
                    Log.e("aaa","noono");
                    getMvpView().onNoDirection();
                    return;
                }
                Log.e("aaa","yeye");
                getMvpView().onHasDirection(result.getRoutes().get(0), color);
            }

            @Override
            public void onPostTask() {

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
