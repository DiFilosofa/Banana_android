package vn.quankundeptrai.banana.ui.menuactivities.feedback;

import vn.quankundeptrai.banana.data.ApiObservable;
import vn.quankundeptrai.banana.data.models.responses.BaseResponse;
import vn.quankundeptrai.banana.interfaces.ITask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;
import vn.quankundeptrai.banana.ui.menuactivities.help.HelpMvpView;

/**
 * Created by TQN on 1/31/18.
 */

public class FeedbackPresenter extends BasePresenter<FeedbackMvpView> {
    void feedback(String input){
         callApi(ApiObservable.feedback(input), new ITask<BaseResponse<Object>>() {
             @Override
             public void onPreTask() {

             }

             @Override
             public void onDone(BaseResponse<Object> result) {
                getMvpView().onFeedbackSuccess();
             }

             @Override
             public void onPostTask() {

             }
         });
    }
}
