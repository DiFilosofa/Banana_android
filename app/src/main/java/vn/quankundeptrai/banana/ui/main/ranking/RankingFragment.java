package vn.quankundeptrai.banana.ui.main.ranking;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.models.responses.UserResponse;
import vn.quankundeptrai.banana.enums.LeaderboardType;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.ui.adapter.LeaderboardAdapter;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 2/13/18.
 */

public class RankingFragment extends BaseFragment<RankingPresenter> implements RankingMvpView, IAdapterDataCallback, View.OnClickListener {
    private LeaderboardAdapter adapter;
    private LeaderboardType currentType = LeaderboardType.ALL_TIME;
    private TextView allTime, year, month;
    private ArrayList<TextView> types = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_ranking;
    }

    @Override
    protected RankingPresenter onCreatePresenter() {
        return new RankingPresenter();
    }

    @Override
    protected void initialView() {
        getCurrentActivity().showLoading();

        allTime = mainView.findViewById(R.id.allTimeBtn);
        year = mainView.findViewById(R.id.yearBtn);
        month = mainView.findViewById(R.id.monthBtn);
        RecyclerView rankingRecycler = mainView.findViewById(R.id.rankingRecycler);
        mainView.findViewById(R.id.allTimeBtn).setOnClickListener(this);
        mainView.findViewById(R.id.yearBtn).setOnClickListener(this);
        mainView.findViewById(R.id.monthBtn).setOnClickListener(this);

        types.add(allTime);
        types.add(year);
        types.add(month);

        rankingRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rankingRecycler.setAdapter(adapter = new LeaderboardAdapter(getContext(), new ArrayList<UserResponse>(), this));

        Calendar calendar = new GregorianCalendar();

        year.setText(String.format(getString(R.string.year), calendar.get(Calendar.YEAR)));
        month.setText(String.format(getString(R.string.month), calendar.get(Calendar.MONTH) + 1));

        getPresenter().getLeaderboard(LeaderboardType.ALL_TIME);
    }

    @Override
    public void onGetLeaderboardSuccess(ArrayList<UserResponse> list) {
        adapter.update(list);
        getCurrentActivity().hideLoading();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allTimeBtn:
                handleRankingTypeClick(LeaderboardType.ALL_TIME);
                break;
            case R.id.monthBtn:
                handleRankingTypeClick(LeaderboardType.MONTH);
                break;
            case R.id.yearBtn:
                handleRankingTypeClick(LeaderboardType.YEAR);
                break;
        }
    }

    private void handleRankingTypeClick(LeaderboardType type) {
        if (currentType != type) {
            getCurrentActivity().showLoading();
            int gray = ContextCompat.getColor(getContext(), R.color.textGray);
            for (TextView item : types) {
                item.setTextColor(gray);
            }
            types.get(type.ordinal()).setTextColor(Color.WHITE);
            currentType = type;
            getPresenter().getLeaderboard(type);
        }
    }
}
