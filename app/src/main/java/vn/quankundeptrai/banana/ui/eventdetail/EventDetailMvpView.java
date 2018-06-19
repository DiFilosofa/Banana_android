package vn.quankundeptrai.banana.ui.eventdetail;

import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.ui.base.BaseMvpView;

/**
 * Created by TQN on 2/14/18.
 */

public interface EventDetailMvpView extends BaseMvpView {
    void onGetEventSuccess(Event event);
}
