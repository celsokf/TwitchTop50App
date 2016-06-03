package br.com.cemobile.twitchtop50app.controller.fragment;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import br.com.cemobile.twitchtop50app.Constant;
import br.com.cemobile.twitchtop50app.R;
import br.com.cemobile.twitchtop50app.adapter.GameAdapter;
import br.com.cemobile.twitchtop50app.controller.event.ErrorEvent;
import br.com.cemobile.twitchtop50app.controller.event.request.RequestGamesListEvent;
import br.com.cemobile.twitchtop50app.controller.event.response.ResponseGamesListEvent;
import br.com.cemobile.twitchtop50app.model.TopGame;
import de.greenrobot.event.EventBus;

/**
 * Created by celso on 29/05/16.
 */

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    private int offset;

    private boolean loading;

    @ViewById
    public RecyclerView gamesRecyclerView;

    private GameAdapter adapter;

    public MainFragment() {
        this.loading = true;
        offset = 0;
        if(!EventBus.getDefault().isRegistered(this)) {
            Log.i("MainFragment", "[MainFragment]EventBus.register");
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @AfterViews
    public void afterViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        this.gamesRecyclerView.setLayoutManager(layoutManager);
        this.gamesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.adapter = new GameAdapter(this.getActivity());
        this.gamesRecyclerView.setAdapter(this.adapter);

        this.addOnScrollListener();
    }

    private void addOnScrollListener() {
        this.gamesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int visibleItemCount, totalItemCount;
            private int pastVisiblesItems = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                this.visibleItemCount = gamesRecyclerView.getLayoutManager().getChildCount();
                this.totalItemCount = gamesRecyclerView.getLayoutManager().getItemCount();
                this.pastVisiblesItems = ((LinearLayoutManager) gamesRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if (loading) {
                    if ((this.visibleItemCount + this.pastVisiblesItems) >= this.totalItemCount) {
                        loading = false;
                        Log.i("MainActivity", "[onScrolled]RequestGamesListEvent");
                        EventBus.getDefault().post(new RequestGamesListEvent(getActivity(), offset));
                    }
                }
            }
        });
    }

    public void onEvent(ErrorEvent event) {
        this.loading = true;
    }

    public void onEvent(ResponseGamesListEvent event) {
        Log.d("MainFragment", "[onEvent]ResponseGamesListEvent");
        this.offset = event.getOffset() + Constant.DEFAULT_OFFSET;
        this.fillAdapter(event.getGamesListContent().getTop());
        this.loading = true;
    }

    @UiThread
    protected void fillAdapter(List<TopGame> gamesList) {
        this.adapter.addAll(gamesList);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            Log.d("MainFragment", "[onStart]EventBus.register");
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            Log.d("MainFragment", "[onStart]EventBus.unregister");
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }
}
