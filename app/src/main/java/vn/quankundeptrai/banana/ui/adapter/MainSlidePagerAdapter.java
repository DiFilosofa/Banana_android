package vn.quankundeptrai.banana.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import vn.quankundeptrai.banana.ui.base.BaseFragment;


/**
 * Created by TQN on 7/3/2017.
 */

public class MainSlidePagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragmentList;

    public MainSlidePagerAdapter(FragmentManager fm, List<BaseFragment> myList) {
        super(fm);
        fragmentList = myList;
    }

    @Override
    public BaseFragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
