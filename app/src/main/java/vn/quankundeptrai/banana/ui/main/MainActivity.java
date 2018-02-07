package vn.quankundeptrai.banana.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.customviews.general.NonSwipeViewPager;
import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.ui.adapter.PagerAdapter;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.menuactivities.favoritelocations.FavoriteLocationActivity;
import vn.quankundeptrai.banana.ui.menuactivities.feedback.FeedbackActivity;
import vn.quankundeptrai.banana.ui.menuactivities.help.HelpActivity;
import vn.quankundeptrai.banana.ui.menuactivities.notification.NotificationActivity;
import vn.quankundeptrai.banana.ui.menuactivities.rewards.RewardsActivity;
import vn.quankundeptrai.banana.utils.InstantiateUtils;
import vn.quankundeptrai.banana.utils.MenuManager;

public class MainActivity extends BaseActivity<MainPresenter> implements MainMvpView, IAdapterDataCallback, View.OnClickListener {
    private MenuManager menuManager;

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
        destroyHeader();
        ImageView menuBtn = mainView.findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(this);

        menuManager = new MenuManager(this, mainView, this);
    }

    @Override
    public void onItemClick(int position) {
        switch (position){
            case AppConstants.MENU_NOTI:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case AppConstants.MENU_FAV_LOCATION:
                startActivity(new Intent(this, FavoriteLocationActivity.class));
                break;
            case AppConstants.MENU_FEEDBACK:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case AppConstants.MENU_REWARDS:
                startActivity(new Intent(this, RewardsActivity.class));
                break;
            case AppConstants.MENU_HELP:
                startActivity(new Intent(this, HelpActivity.class));
                break;
            case AppConstants.MENU_FACEBOOK:
                openFacebook();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menuBtn :
                menuManager.openMenu();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Reload map
    }

    private void openFacebook(){
        Toast.makeText(this, "Opening Facebook...", Toast.LENGTH_SHORT).show();
    }
}
