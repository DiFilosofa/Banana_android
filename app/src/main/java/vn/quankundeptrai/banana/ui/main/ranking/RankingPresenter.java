package vn.quankundeptrai.banana.ui.main.ranking;


import java.util.ArrayList;

import io.reactivex.Observable;
import vn.quankundeptrai.banana.data.ApiObservable;
import vn.quankundeptrai.banana.data.models.responses.BaseResponse;
import vn.quankundeptrai.banana.data.models.responses.UserResponse;
import vn.quankundeptrai.banana.enums.LeaderboardType;
import vn.quankundeptrai.banana.interfaces.ITask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 2/13/18.
 */

public class RankingPresenter extends BasePresenter<RankingMvpView> {
    void getLeaderboard(final LeaderboardType type) {
        callBaseApi(ApiObservable.getLeaderboard(System.currentTimeMillis(), type), new ITask<ArrayList<UserResponse>>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(ArrayList<UserResponse> result) {
                getMvpView().onGetLeaderboardSuccess(result);
            }

            @Override
            public void onPostTask() {

            }
        });
    }
}
