package vn.quankundeptrai.banana.ui.main.map;

import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleDirectionRoutes;
import vn.quankundeptrai.banana.ui.base.BaseMvpView;

/**
 * Created by TQN on 2/13/18.
 */

public interface MapMvpView extends BaseMvpView {
    void onHasDirection(GoogleDirectionRoutes route, Integer color);
    void onNoDirection();
}
