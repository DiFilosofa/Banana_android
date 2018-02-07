package vn.quankundeptrai.banana.utils;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.models.menu.MenuItemModel;
import vn.quankundeptrai.banana.ui.menuactivities.favoritelocations.FavoriteLocationActivity;
import vn.quankundeptrai.banana.ui.menuactivities.feedback.FeedbackActivity;
import vn.quankundeptrai.banana.ui.menuactivities.help.HelpActivity;
import vn.quankundeptrai.banana.ui.main.map.MapFragment;
import vn.quankundeptrai.banana.ui.menuactivities.notification.NotificationActivity;
import vn.quankundeptrai.banana.ui.menuactivities.rewards.RewardsActivity;

/**
 * Created by TQN on 1/20/2018.
 */

public class InstantiateUtils {
    public static List<MenuItemModel> generateMenuItems() {
        List<MenuItemModel> menuItems = new ArrayList<MenuItemModel>();
        menuItems.add(new MenuItemModel(R.drawable.ic_bell, "Notification"));
        menuItems.add(new MenuItemModel(R.drawable.ic_heart, "Favorite Locations"));
        menuItems.add(new MenuItemModel(R.drawable.ic_facebook, "Our Facebook"));
        menuItems.add(new MenuItemModel(R.drawable.ic_bulb, "Your Thoughts"));
        menuItems.add(new MenuItemModel(R.drawable.ic_reward, "Rewards"));
        menuItems.add(new MenuItemModel(R.drawable.ic_help, "Help"));
        return menuItems;
    }
}
