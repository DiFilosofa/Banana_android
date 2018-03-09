package vn.quankundeptrai.banana.ui.createevent;


import java.io.File;

import vn.quankundeptrai.banana.data.ApiObservable;
import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleLatLng;
import vn.quankundeptrai.banana.enums.RxStatus;
import vn.quankundeptrai.banana.interfaces.ITask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 3/6/18.
 */

public class CreateNewEventPresenter extends BasePresenter<CreateNewEventMvpView> {
    void postEvent(File image, String eventName, GoogleLatLng startLatLng, GoogleLatLng endLatLng, Float distance,
                   int density, int carSpeed, int motorSpeed,
                   boolean hasRain, boolean hasFlood, boolean hasAccident, boolean hasPolice, boolean shouldTravel) {

        callApi(ApiObservable.postEvent(image, eventName, startLatLng, endLatLng,
                distance, density, carSpeed, motorSpeed,
                hasRain, hasFlood, hasAccident, hasPolice, shouldTravel), new ITask<RxStatus>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(RxStatus result) {
                getMvpView().onPostEventDone(result == RxStatus.Success);
            }


            @Override
            public void onPostTask() {

            }
        });
    }
}
