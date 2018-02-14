package vn.quankundeptrai.banana.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.ui.base.BaseRecyclerAdapter;
import vn.quankundeptrai.banana.ui.base.BaseViewHolder;
import vn.quankundeptrai.banana.utils.DateTimeUtils;

/**
 * Created by TQN on 2/13/18.
 */

public class EventsAdapter extends BaseRecyclerAdapter<Event, EventsAdapter.EventViewHolder> {
    private IAdapterDataCallback callback;

    public EventsAdapter(Context context, List<Event> list, IAdapterDataCallback callback) {
        super(context, list);
        this.callback = callback;
    }

    @Override
    protected EventViewHolder getNewHolder(View convertView) {
        return new EventViewHolder(convertView);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.item_event;
    }

    @Override
    protected void handleItem(EventViewHolder holder, final int position, Event item) {

        holder.rain.setVisibility(item.hasRain() ? View.VISIBLE : View.INVISIBLE);
        holder.accident.setVisibility(item.hasAccident() ? View.VISIBLE : View.INVISIBLE);
        holder.flood.setVisibility(item.hasFlood() ? View.VISIBLE : View.INVISIBLE);
        holder.name.setText(item.getName());
        holder.timeAgo.setText(DateTimeUtils.getTimeAgo(context, item.getCreatedAt()));
        holder.point.setText(String.format(context.getString(R.string.point), item.getPoint().getPointSum()));

        Log.e("ecec", item.getId()+item.getDensity());

        switch (item.getDensity()){
            case FREE:
                holder.density.setColorFilter(ContextCompat.getColor(context, R.color.greenDensity));
                break;
            case NORMAL:
                holder.density.setColorFilter(ContextCompat.getColor(context, R.color.yellowDensity));
                break;
            case QUITE_CROWDED:
                holder.density.setColorFilter(ContextCompat.getColor(context, R.color.orangeDensity));
                break;
            case CROWDED:
                holder.density.setColorFilter(ContextCompat.getColor(context, R.color.hardOrangeDensity));
                break;
            default:
                holder.density.setColorFilter(ContextCompat.getColor(context, R.color.redDensity));
                break;
        }

        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(position);
            }
        });

    }

    class EventViewHolder extends BaseViewHolder {
        TextView name, timeAgo, point;
        View line, wrapper;
        ImageView upvote, downvote, accident, rain, flood, density;

        EventViewHolder(View itemView) {
            super(itemView);
            wrapper = itemView.findViewById(R.id.wrapper);
            name = itemView.findViewById(R.id.eventName);
            timeAgo = itemView.findViewById(R.id.eventTimeAgo);
            point = itemView.findViewById(R.id.eventPoints);
            line = itemView.findViewById(R.id.line);
            upvote = itemView.findViewById(R.id.eventUpvoteBtn);
            downvote = itemView.findViewById(R.id.eventDownvoteBtn);
            accident = itemView.findViewById(R.id.eventAccident);
            rain = itemView.findViewById(R.id.eventRain);
            flood = itemView.findViewById(R.id.eventFlood);
            density = itemView.findViewById(R.id.eventDensity);
        }
    }
}
