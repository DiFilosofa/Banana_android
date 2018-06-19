package vn.quankundeptrai.banana.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by TQN on 1/19/2018.
 */

public abstract class BaseRecyclerAdapter <T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    protected List<T> mainList;
    protected Context context;

    public BaseRecyclerAdapter(Context context, List<T> list) {
        this.mainList = list;
        this.context = context;
    }

    protected abstract V getNewHolder(View convertView);

    protected abstract int getItemLayoutResource();

    protected abstract void handleItem(V holder, int position, T item);

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        return getNewHolder(LayoutInflater.from(context).inflate(getItemLayoutResource(), parent, false));
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        handleItem(holder, position, getItem(position));
    }

    public T getItem(int position) {
        return mainList.get(position);
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public List<T> getMainList() {
        return mainList;
    }

    public void addItems(List<T> items) {
        this.mainList.addAll(items);
        notifyDataSetChanged();
    }

    public void clearMainList() {
        this.mainList.clear();
        notifyDataSetChanged();
    }

    public void update(List<T> list) {
        this.mainList = list;
        notifyDataSetChanged();
    }

    public void addItem(T newItem) {
        this.mainList.add(newItem);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.mainList.remove(position);
        notifyDataSetChanged();
    }
}
