package br.com.cemobile.twitchtop50app.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import br.com.cemobile.twitchtop50app.model.TopGame;
import br.com.cemobile.twitchtop50app.view.GameItemView;
import br.com.cemobile.twitchtop50app.view.GameItemView_;
import br.com.cemobile.twitchtop50app.view.ViewWrapper;

/**
 * Created by celso on 29/05/16.
 */
public class GameAdapter extends RecyclerViewAdapterBase<TopGame, GameItemView> {

    protected Context context;

    public GameAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected GameItemView onCreateItemView(ViewGroup parent, int viewType) {
        return GameItemView_.build(this.context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<GameItemView> viewHolder, int position) {
        GameItemView view = viewHolder.getView();
        if (position < this.items.size()) {
            TopGame topGame = this.items.get(position);
            topGame.setPosition(position + 1);
            view.bind(topGame);
        }
    }

    public void addAll(List<TopGame> games) {
        for (TopGame topGame: games) {
            this.items.add(topGame);
        }
    }

}
