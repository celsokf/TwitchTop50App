package br.com.cemobile.twitchtop50app.controller.event;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.cemobile.twitchtop50app.Constant;
import br.com.cemobile.twitchtop50app.R;
import br.com.cemobile.twitchtop50app.controller.component.DaggerTwitchComponent;
import br.com.cemobile.twitchtop50app.controller.component.TwitchComponent;
import br.com.cemobile.twitchtop50app.controller.event.request.RequestGamesListEvent;
import br.com.cemobile.twitchtop50app.controller.event.response.ResponseGamesListEvent;
import br.com.cemobile.twitchtop50app.controller.module.TwitchModule;
import br.com.cemobile.twitchtop50app.model.GamesListContent;
import br.com.cemobile.twitchtop50app.service.TwitchRestClient;
import br.com.cemobile.twitchtop50app.util.GenericUtil;
import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by celso on 28/05/16.
 */
public class EventManager {

    private static EventManager instance;

    private TwitchRestClient restClient;

    protected Context context;

    private EventManager() {}

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }

        return instance;
    }

    public void init() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        if (this.restClient == null) {
            TwitchComponent twitchComponent = DaggerTwitchComponent.builder()
                    .twitchModule(new TwitchModule())
                    .build();

            this.restClient = twitchComponent.provideTwitchRestClient();
        }

    }

    public void onEvent(final RequestGamesListEvent event) {
        this.context = event.getContext();

        if (!GenericUtil.isOnline(this.context)) {
            EventBus.getDefault().post(new ErrorEvent(this.context.getString(R.string.offline)));
            return;
        }

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(Constant.LIMIT_PARAMETER, Constant.DEFAULT_LIMIT);
        parameters.put(Constant.OFFSET_PARAMETER, String.valueOf(event.getOffset()));

        Call<GamesListContent> call = this.restClient.getTopGames(parameters);
        call.enqueue(new Callback<GamesListContent>() {
            @Override
            public void onResponse(Call<GamesListContent> call, Response<GamesListContent> response) {
                Log.d("EventManager", "[onResponse]sucess=" + response.isSuccessful());
                if (response.isSuccessful()) {
                    GamesListContent gamesListContent = response.body();
                    EventBus.getDefault().post(new ResponseGamesListEvent(gamesListContent, event.getOffset()));
                }
            }

            @Override
            public void onFailure(Call<GamesListContent> call, Throwable t) {
                t.printStackTrace();
                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
            }
        });

    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
    }
}
