package vn.quankundeptrai.banana.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.models.responses.UserResponse;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.ui.base.BaseRecyclerAdapter;
import vn.quankundeptrai.banana.ui.base.BaseViewHolder;

/**
 * Created by TQN on 2/15/18.
 */

public class LeaderboardAdapter extends BaseRecyclerAdapter<UserResponse, LeaderboardAdapter.LeaderboardViewHolder> {
    private IAdapterDataCallback callback;

    public LeaderboardAdapter(Context context, List<UserResponse> list, IAdapterDataCallback callback) {
        super(context, list);
        this.callback = callback;
    }

    @Override
    protected LeaderboardViewHolder getNewHolder(View convertView) {
        return new LeaderboardViewHolder(convertView);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.item_leaderboard;
    }

    @Override
    protected void handleItem(LeaderboardViewHolder holder, int position, UserResponse item) {
        holder.name.setText(item.getName());
        holder.rankingDetail.setText(String.format(context.getString(R.string.rankingDetail), item.getPoint(), item.getLevel().toString()));
        if (!item.getAvatarSrc().isEmpty()) {
            int size = (int) context.getResources().getDimension(R.dimen.avatar_size);
            Picasso.with(context).load(item.getAvatarSrc()).resize(size, size).centerCrop().into(holder.avatar);
        }
    }

    public class LeaderboardViewHolder extends BaseViewHolder {
        CircleImageView avatar;
        TextView rankingDetail, name;
        View wrapper;

        public LeaderboardViewHolder(View itemView) {
            super(itemView);
            wrapper = itemView.findViewById(R.id.wrapper);
            avatar = itemView.findViewById(R.id.avartar);
            rankingDetail = itemView.findViewById(R.id.rankDetail);
            name = itemView.findViewById(R.id.username);
        }
    }
}
