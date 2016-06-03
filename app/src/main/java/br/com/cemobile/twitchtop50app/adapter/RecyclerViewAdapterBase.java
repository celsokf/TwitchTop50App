package br.com.cemobile.twitchtop50app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.cemobile.twitchtop50app.view.ViewWrapper;

/**
 * Created by celso on 29/05/16.
 */
public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> items = new ArrayList<T>();

    @Override
    public ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        int size = this.items.size();

        return size > 0 ? this.items.size() : 1;
    }
}
