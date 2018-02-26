package vn.quankundeptrai.banana.ui.main.eventslist;

import vn.quankundeptrai.banana.data.ApiObservable;
import vn.quankundeptrai.banana.interfaces.ITask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 2/13/18.
 */

public class EventListPresenter extends BasePresenter<EventListMvpView> {
    void upvote(final String eventId){
        callBaseApi(ApiObservable.upvote(eventId), new ITask<Object>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(Object result) {
                getMvpView().onVoteSuccess();
            }

            @Override
            public void onPostTask() {

            }
        });
    }

    void downvote(final String eventId){
        callBaseApi(ApiObservable.downvote(eventId), new ITask<Object>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(Object result) {
                getMvpView().onVoteSuccess();
            }

            @Override
            public void onPostTask() {

            }
        });
    }
}
