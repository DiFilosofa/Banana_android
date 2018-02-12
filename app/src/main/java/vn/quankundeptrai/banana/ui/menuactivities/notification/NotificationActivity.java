package vn.quankundeptrai.banana.ui.menuactivities.notification;

import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 1/31/18.
 */

public class NotificationActivity extends BaseActivity<NotificationPresenter> implements NotificationMvpView, CompoundButton.OnCheckedChangeListener {
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_notifications;
    }

    @Override
    protected String getScreenTitle() {
        return getString(R.string.title_notification);
    }

    @Override
    protected NotificationPresenter onCreatePresenter() {
        return new NotificationPresenter();
    }

    @Override
    protected void initialView() {
        SwitchCompat switchButton = mainView.findViewById(R.id.getNotiSwitch);
        switchButton.setOnCheckedChangeListener(this);
        switchButton.setChecked(CoreManager.getInstance().isNotificationOn());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CoreManager.getInstance().setNotificationStatus(isChecked);
    }
}
