package vn.quankundeptrai.banana.ui.eventdetail;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.constants.ExtraKeys;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.utils.DateTimeUtils;

/**
 * Created by TQN on 2/14/18.
 */

public class EventDetailActivity extends BaseActivity<EventDetailPresenter> implements EventDetailMvpView {
    private TextView density, motorbikeSpeed, carSpeed, hasRain, hasFlood, hasAccident, recommendation, timeAgo;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_event_detail;
    }

    @Override
    protected String getScreenTitle() {
        return "";
    }

    @Override
    protected EventDetailPresenter onCreatePresenter() {
        return new EventDetailPresenter();
    }

    @Override
    protected void initialView() {
        showLoading();
        density = mainView.findViewById(R.id.density);
        motorbikeSpeed = mainView.findViewById(R.id.motorbikeSpeed);
        carSpeed = mainView.findViewById(R.id.carSpeed);
        hasRain = mainView.findViewById(R.id.hasRain);
        hasFlood = mainView.findViewById(R.id.hasFlood);
        hasAccident = mainView.findViewById(R.id.hasAccident);
        recommendation = mainView.findViewById(R.id.recommendation);
        timeAgo = mainView.findViewById(R.id.timeAgo);
        Log.e("Eeee", getIntent().getStringExtra(ExtraKeys.EVENT_ID));

        getPresenter().getAnEvent(getIntent().getStringExtra(ExtraKeys.EVENT_ID));
    }

    @Override
    public void onGetEventSuccess(Event event) {
        String[] densityType = getResources().getStringArray(R.array.density);
        String[] vehicleSpeedType = getResources().getStringArray(R.array.vehicleSpeed);
        String yes = getString(R.string.yes);
        String no = getString(R.string.no);
        String moveable = getString(R.string.movable);
        String unmoveable = getString(R.string.unmovable);

        hideLoading();
        setTitle(event.getName());
        timeAgo.setText(DateTimeUtils.getTimeAgo(this, event.getCreatedAt()));
        density.setText(densityType[event.getDensity().ordinal()]);
        carSpeed.setText(vehicleSpeedType[event.getCarSpeed().ordinal()]);
        motorbikeSpeed.setText(vehicleSpeedType[event.getMotorbikeSpeed().ordinal()]);
        hasRain.setText(event.hasRain() ? yes : no);
        hasFlood.setText(event.hasFlood() ? yes : no);
        hasAccident.setText(event.hasAccident() ? yes : no);
        recommendation.setText(event.shouldTravel() ? moveable : unmoveable);
    }
}
