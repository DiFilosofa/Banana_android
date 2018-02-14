package vn.quankundeptrai.banana.ui.main.eventslist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.constants.ExtraKeys;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.ui.adapter.EventsAdapter;
import vn.quankundeptrai.banana.ui.base.BaseFragment;
import vn.quankundeptrai.banana.ui.eventdetail.EventDetailActivity;
import vn.quankundeptrai.banana.ui.main.MainActivity;

/**
 * Created by TQN on 2/13/18.
 */

public class EventListFragment extends BaseFragment<EventListPresenter> implements EventListMvpView, IAdapterDataCallback, View.OnClickListener {
    private EventsAdapter adapter;
    private View emptyList;

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
        emptyList = mainView.findViewById(R.id.emptyList);
        RecyclerView recycler = mainView.findViewById(R.id.listEventRecycler);
        mainView.findViewById(R.id.refreshBtn).setOnClickListener(this);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(adapter = new EventsAdapter(getContext(), new ArrayList<Event>(), this));
    }

    public void updateEvent(ArrayList<Event> list){
        emptyList.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
        adapter.update(list);
    }

    @Override
    public void onItemClick(int position) {
        getCurrentActivity().startBaseActivityWithExtra(getContext(), EventDetailActivity.class, ExtraKeys.EVENT_ID, adapter.getItem(position).getId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.refreshBtn:
                ((MainActivity)getCurrentActivity()).refresh();
                break;
        }
    }
}
