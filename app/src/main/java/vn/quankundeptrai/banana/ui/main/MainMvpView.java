package vn.quankundeptrai.banana.ui.main;

import java.util.ArrayList;
import java.util.List;

import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.ui.base.BaseMvpView;

/**
 * Created by TQN on 1/19/2018.
 */

public interface MainMvpView extends BaseMvpView {
    void onGetEventsSuccess(ArrayList<Event> list);
}
