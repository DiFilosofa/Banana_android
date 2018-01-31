package vn.quankundeptrai.banana.utils;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.models.menu.MenuItemModel;
import vn.quankundeptrai.banana.ui.menufragments.favoritelocations.FavoriteLocationFragment;
import vn.quankundeptrai.banana.ui.menufragments.feedback.FeedbackFragment;
import vn.quankundeptrai.banana.ui.menufragments.help.HelpFragment;
import vn.quankundeptrai.banana.ui.menufragments.map.MapFragment;
import vn.quankundeptrai.banana.ui.menufragments.notification.NotificationFragment;
import vn.quankundeptrai.banana.ui.menufragments.rewards.RewardsFragment;

/**
 * Created by TQN on 1/20/2018.
 */

public class InstantiateUtils {
    public static List<MenuItemModel> generateMenuItems() {
        List<MenuItemModel> menuItems = new ArrayList<MenuItemModel>();
        menuItems.add(new MenuItemModel(R.drawable.ic_map, "Map"));
        menuItems.add(new MenuItemModel(R.drawable.ic_bell, "Notification"));
        menuItems.add(new MenuItemModel(R.drawable.ic_heart, "Favorite Locations"));
        menuItems.add(new MenuItemModel(R.drawable.ic_facebook, "Our Facebook"));
        menuItems.add(new MenuItemModel(R.drawable.ic_bulb, "Your Thoughts"));
        menuItems.add(new MenuItemModel(R.drawable.ic_reward, "Rewards"));
        menuItems.add(new MenuItemModel(R.drawable.ic_help, "Help"));
        return menuItems;
    }

    public static ArrayList<Fragment> generateMenuFragments() {
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new MapFragment());
        fragmentList.add(new NotificationFragment());
        fragmentList.add(new FavoriteLocationFragment());
        fragmentList.add(new Fragment());
        fragmentList.add(new FeedbackFragment());
        fragmentList.add(new RewardsFragment());
        fragmentList.add(new HelpFragment());
        return fragmentList;
    }
}
