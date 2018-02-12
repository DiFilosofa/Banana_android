package vn.quankundeptrai.banana.ui.menuactivities.notification;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 1/31/18.
 */

public class NotificationActivity extends BaseActivity<NotificationPresenter> implements NotificationMvpView {
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
        
    }
}
