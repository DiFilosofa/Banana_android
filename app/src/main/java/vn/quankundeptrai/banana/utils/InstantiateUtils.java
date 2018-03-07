package vn.quankundeptrai.banana.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.models.favoriteLocation.FavoriteLocationItemModel;
import vn.quankundeptrai.banana.data.models.menu.MenuItemModel;
import vn.quankundeptrai.banana.ui.base.BaseFragment;
import vn.quankundeptrai.banana.ui.main.eventslist.EventListFragment;
import vn.quankundeptrai.banana.ui.main.map.MapFragment;
import vn.quankundeptrai.banana.ui.main.ranking.RankingFragment;

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

    public static List<FavoriteLocationItemModel> generateFavoriteLocations(Context context) {
        List<FavoriteLocationItemModel> districts = new ArrayList<FavoriteLocationItemModel>();
        String[] districtArray = context.getResources().getStringArray(R.array.districts);
        for (String dist : districtArray) {
            districts.add(new FavoriteLocationItemModel(dist, ""));
        }
        return districts;
    }

    public static List<BaseFragment> createNavigationFragment(){
        List<BaseFragment> navigationItems = new ArrayList<BaseFragment>();
        navigationItems.add(new MapFragment());
        navigationItems.add(new EventListFragment());
        navigationItems.add(new RankingFragment());
        return navigationItems;
    }
}
