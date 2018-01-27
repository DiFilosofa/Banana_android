package vn.quankundeptrai.banana.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.customviews.general.NonSwipeViewPager;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.ui.adapter.PagerAdapter;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.utils.InstantiateUtils;
import vn.quankundeptrai.banana.utils.MenuManager;

public class MainActivity extends BaseActivity<MainPresenter> implements MainMvpView, IAdapterDataCallback, View.OnClickListener {
    private NonSwipeViewPager pager;
    private PagerAdapter adapter;
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
        pager = mainView.findViewById(R.id.pager);

        ImageView menuBtn = (ImageView)mainView.findViewById(R.id.headerLeftBtn);
        menuBtn.setImageResource(R.drawable.ic_menu);
        menuBtn.setOnClickListener(this);

        menuManager = new MenuManager(this, mainView, this);

        pager.setAdapter(adapter = new PagerAdapter(getSupportFragmentManager(), InstantiateUtils.generateMenuFragments()));
        pager.setOffscreenPageLimit(adapter.getCount() - 1);
    }

    public void setTitle(String title){
        ((TextView) mainView.findViewById(R.id.headerTitle)).setText(title);
    }

    @Override
    public void onItemClick(int position) {
        switch (position){
            ////set menu item click behavior here
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
}
