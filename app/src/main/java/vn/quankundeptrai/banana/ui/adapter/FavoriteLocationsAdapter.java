package vn.quankundeptrai.banana.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.models.favoriteLocation.FavoriteLocationItemModel;
import vn.quankundeptrai.banana.interfaces.IFavoriteLocationCallback;
import vn.quankundeptrai.banana.ui.base.BaseRecyclerAdapter;
import vn.quankundeptrai.banana.ui.base.BaseViewHolder;

/**
 * Created by TQN on 2/12/18.
 */

public class FavoriteLocationsAdapter extends BaseRecyclerAdapter<FavoriteLocationItemModel, FavoriteLocationsAdapter.FavoriteLocationViewHolder> {
    private IFavoriteLocationCallback callback;

    public FavoriteLocationsAdapter(Context context, List<FavoriteLocationItemModel> list, IFavoriteLocationCallback callback) {
        super(context, list);
        this.callback = callback;
    }

    @Override
    protected FavoriteLocationViewHolder getNewHolder(View convertView) {
        return new FavoriteLocationViewHolder(convertView);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.item_favorite_districts;
    }

    @Override
    protected void handleItem(final FavoriteLocationViewHolder holder, final int position, final FavoriteLocationItemModel item) {
        holder.isFavorite.setChecked(item.isFavorite());
        holder.name.setText(item.getDistrict());
        holder.detail.setText(item.getDetail());

        holder.isFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFavorite(position, holder.isFavorite.isChecked());
            }
        });
    }

    private void updateFavorite(int position, boolean isChecked) {
        if (position == 0) {
            for (int pos = 0; pos < mainList.size(); pos++) {
                mainList.get(pos).setFavorite(isChecked);
            }
        } else {
            FavoriteLocationItemModel all = mainList.get(0);
            if(all.isFavorite() && !isChecked){
                all.setFavorite(false);
            }
            mainList.get(position).setFavorite(isChecked);
        }
        notifyDataSetChanged();
    }

    class FavoriteLocationViewHolder extends BaseViewHolder {
        private TextView name, detail;
        private CheckBox isFavorite;

        FavoriteLocationViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.favoriteDistrict);
            detail = itemView.findViewById(R.id.favoriteDetails);
            isFavorite = itemView.findViewById(R.id.favoriteCheckbox);
        }
    }
}
