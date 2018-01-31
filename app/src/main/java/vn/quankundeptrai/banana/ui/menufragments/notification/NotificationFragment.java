package vn.quankundeptrai.banana.ui.menufragments.notification;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseFragment;

/**
 * Created by TQN on 1/31/18.
 */

public class NotificationFragment extends BaseFragment<NotificationPresenter> implements NotificationMvpView {
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected NotificationPresenter onCreatePresenter() {
        return new NotificationPresenter();
    }

    @Override
    protected void initialView() {

    }
}
