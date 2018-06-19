package vn.quankundeptrai.banana.utils;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.main.MainActivity;
import vn.quankundeptrai.banana.ui.adapter.MenuAdapter;

/**
 * Created by TQN on 1/20/2018.
 */

public class MenuManager implements DrawerLayout.DrawerListener, IAdapterDataCallback {
    private IAdapterDataCallback callback;
    private BaseActivity activity;
    private MenuAdapter adapter;
    private DrawerLayout menu;
    private View mainView;
    private int currentPosition = 0;

    public MenuManager(BaseActivity activity, View mainView, IAdapterDataCallback callback) {
        this.activity = activity;
        this.adapter = new MenuAdapter(activity, InstantiateUtils.generateMenuItems(), this);
        this.mainView = mainView;
        this.callback = callback;
        initMenu();
    }

    private void initMenu() {
        RecyclerView menuRecycler = mainView.findViewById(R.id.menuItemsRecycler);
        menuRecycler.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        menuRecycler.setAdapter(adapter);

        (menu = mainView.findViewById(R.id.drawer)).addDrawerListener(this);
    }

    public void selectTab(int position) {
        closeMenu();
        if (currentPosition != position) {
            currentPosition = position;
            callback.onItemClick(position);
        }
    }

    public void closeMenu() {
        menu.closeDrawer(Gravity.START);
    }

    public void openMenu() {
        menu.openDrawer(Gravity.START);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        ((LinearLayout) mainView.findViewById(R.id.navigation)).setX(drawerView.getWidth() * (1 - slideOffset));

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onItemClick(int position) {
        callback.onItemClick(position);
    }
}
