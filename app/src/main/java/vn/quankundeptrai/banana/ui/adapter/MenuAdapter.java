package vn.quankundeptrai.banana.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.data.models.menu.MenuItemModel;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.ui.base.BaseRecyclerAdapter;
import vn.quankundeptrai.banana.ui.base.BaseViewHolder;

/**
 * Created by TQN on 1/19/2018.
 */

public class MenuAdapter extends BaseRecyclerAdapter<MenuItemModel, MenuAdapter.MenuItemViewHolder> {
    private IAdapterDataCallback callback;
    public MenuAdapter(Context context, List<MenuItemModel> list, IAdapterDataCallback callback) {
        super(context, list);
        this.callback = callback;
    }

    @Override
    protected MenuItemViewHolder getNewHolder(View convertView) {
        return new MenuItemViewHolder(convertView);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.item_menu;
    }

    @Override
    protected void handleItem(final MenuItemViewHolder holder, final int position, MenuItemModel item) {

        holder.icon.setImageResource(item.getIconResId());
        holder.name.setText(item.getItemName());

        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(position);
            }
        });
    }

    class MenuItemViewHolder extends BaseViewHolder {
        private ImageView icon;
        private TextView name;
        private View wrapper;

        MenuItemViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.menuItemIcon);
            name = itemView.findViewById(R.id.menuItemName);
            wrapper = itemView.findViewById(R.id.menuItemWrapper);
        }
    }
}
