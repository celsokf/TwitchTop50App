package br.com.cemobile.twitchtop50app.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by celso on 29/05/16.
 */
public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V view) {
        super(view);
        this.view = view;
    }

    public V getView() {
        return this.view;
    }
}
