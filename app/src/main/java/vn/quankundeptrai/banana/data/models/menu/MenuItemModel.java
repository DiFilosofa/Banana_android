package vn.quankundeptrai.banana.data.models.menu;

/**
 * Created by TQN on 1/19/2018.
 */

public class MenuItemModel {
    private int iconResId;
    private String itemName;

    public MenuItemModel(int iconResId, String itemName) {
        this.iconResId = iconResId;
        this.itemName = itemName;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
