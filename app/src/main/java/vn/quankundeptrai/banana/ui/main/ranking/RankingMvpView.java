package vn.quankundeptrai.banana.ui.main.ranking;

import java.util.ArrayList;

import vn.quankundeptrai.banana.data.models.responses.UserResponse;
import vn.quankundeptrai.banana.ui.base.BaseMvpView;

/**
 * Created by TQN on 2/13/18.
 */

public interface RankingMvpView extends BaseMvpView {
    void onGetLeaderboardSuccess(ArrayList<UserResponse> list);
}
