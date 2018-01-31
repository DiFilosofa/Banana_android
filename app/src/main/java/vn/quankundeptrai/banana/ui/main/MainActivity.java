package vn.quankundeptrai.banana.ui.main;

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
import vn.quankundeptrai.banana.utils.InstantiateUtils;
import vn.quankundeptrai.banana.utils.MenuManager;

public class MainActivity extends BaseActivity<MainPresenter> implements MainMvpView, IAdapterDataCallback, View.OnClickListener {
    private NonSwipeViewPager pager;
    private PagerAdapter adapter;
    private MenuManager menuManager;
    private View header, headerShadow;
    private TextView headerTitle;
    private String[] menuTitles;

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
        pager = mainView.findViewById(R.id.pager);
        header = mainView.findViewById(R.id.header);
        headerShadow = mainView.findViewById(R.id.headerShadow);
        headerTitle = mainView.findViewById(R.id.headerTitle);

        ImageView menuBtn = (ImageView)mainView.findViewById(R.id.headerLeftBtn);
        menuBtn.setImageResource(R.drawable.ic_menu);
        menuBtn.setOnClickListener(this);

        menuManager = new MenuManager(this, mainView, this);

        pager.setAdapter(adapter = new PagerAdapter(getSupportFragmentManager(), InstantiateUtils.generateMenuFragments()));
        pager.setOffscreenPageLimit(adapter.getCount() - 1);
        menuTitles = getResources().getStringArray(R.array.menuTitle);
        hideMainHeader(true);
    }

    @Override
    public void onItemClick(int position) {
        switch (position){
            case AppConstants.MENU_MAP:
            case AppConstants.MENU_NOTI:
            case AppConstants.MENU_FAV_LOCATION:
            case AppConstants.MENU_FEEDBACK:
            case AppConstants.MENU_REWARDS:
            case AppConstants.MENU_HELP:
                hideMainHeader(position == AppConstants.MENU_MAP);
                pager.setCurrentItem(position);
                headerTitle.setText(menuTitles[position]);
                break;
            case AppConstants.MENU_FACEBOOK:
                openFacebook();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.headerLeftBtn :
                menuManager.openMenu();
                break;
        }
    }

    private void hideMainHeader(boolean hide){
        header.setVisibility(hide ? View.GONE : View.VISIBLE);
        headerShadow.setVisibility(hide ? View.GONE : View.VISIBLE);
    }

    private void openFacebook(){
        Toast.makeText(this, "Opening Facebook...", Toast.LENGTH_SHORT).show();
    }
}
