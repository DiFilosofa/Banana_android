package vn.quankundeptrai.banana.ui.main.eventslist;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 2/13/18.
 */

public class EventListFragment extends BaseFragment<EventListPresenter> implements EventListMvpView{
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_list_events;
    }

    @Override
    protected EventListPresenter onCreatePresenter() {
        return new EventListPresenter();
    }

    @Override
    protected void initialView() {

    }
}
