package vn.quankundeptrai.banana.ui.main;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.customviews.general.NonSwipeViewPager;
import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.ui.adapter.MainSlidePagerAdapter;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.main.eventslist.EventListFragment;
import vn.quankundeptrai.banana.ui.main.map.MapFragment;
import vn.quankundeptrai.banana.utils.InstantiateUtils;


public class MainActivity extends BaseActivity<MainPresenter> implements MainMvpView, View.OnClickListener {
    private NonSwipeViewPager pager;
    private MainSlidePagerAdapter mPagerAdapter;
    private View header;
    private ImageView homeImg, listImg, rankImg;
    private ImageView currentItem;
    private TextView title;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initialView() {
        pager = mainView.findViewById(R.id.pager);
        header = mainView.findViewById(R.id.mainHeader);
        title = mainView.findViewById(R.id.headerTitle);
        homeImg = mainView.findViewById(R.id.homeImg);
        listImg = mainView.findViewById(R.id.eventImg);
        rankImg = mainView.findViewById(R.id.rankImg);
        mainView.findViewById(R.id.headerLeftBtn).setVisibility(View.GONE);
        mainView.findViewById(R.id.homeBtn).setOnClickListener(this);
        mainView.findViewById(R.id.eventBtn).setOnClickListener(this);
        mainView.findViewById(R.id.rankBtn).setOnClickListener(this);

        hideHeader(true);

        pager.setAdapter(mPagerAdapter = new MainSlidePagerAdapter(getSupportFragmentManager(), InstantiateUtils.createNavigationFragment()));
        pager.setOffscreenPageLimit(mPagerAdapter.getCount() - 1);
        currentItem = homeImg;

        refresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeBtn:
                pager.setCurrentItem(AppConstants.NAVIGATION_HOME);
                hideHeader(true);
                recolor(homeImg);
                break;
            case R.id.eventBtn:
                pager.setCurrentItem(AppConstants.NAVIGATION_EVENT);
                recolor(listImg);
                title.setText(R.string.traffic_info);
                hideHeader(false);
                break;
            case R.id.rankBtn:
                pager.setCurrentItem(AppConstants.NAVIGATION_RANK);
                recolor(rankImg);
                title.setText(R.string.leaderboard);
                hideHeader(false);
                break;
        }
    }

    private void hideHeader(boolean hide) {
        header.setVisibility(hide ? View.GONE : View.VISIBLE);
    }

    private void recolor(ImageView newItem) {
        newItem.setColorFilter(ContextCompat.getColor(this, R.color.navigationItemClickedOrange));
        currentItem.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary));
        currentItem = newItem;
    }

    @Override
    protected void setTitle(String text) {
        title.setText(text);
    }

    @Override
    public void onGetEventsSuccess(ArrayList<Event> list) {
        hideLoading();
        ((MapFragment) mPagerAdapter.getItem(AppConstants.NAVIGATION_HOME)).updateEvent(list);
        ((EventListFragment) mPagerAdapter.getItem(AppConstants.NAVIGATION_EVENT)).updateEvent(list);
    }

    public void refresh() {
        showLoading();
        getPresenter().getAllEvents();
    }
}
