package vn.quankundeptrai.banana.ui.eventdetail;

import vn.quankundeptrai.banana.data.ApiObservable;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.interfaces.ITask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 2/14/18.
 */

public class EventDetailPresenter extends BasePresenter<EventDetailMvpView> {
    void getAnEvent(String id){
        callBaseApi(ApiObservable.getAnEvent(id), new ITask<Event>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(Event result) {
                getMvpView().onGetEventSuccess(result);
            }

            @Override
            public void onPostTask() {

            }
        });
    }
}
