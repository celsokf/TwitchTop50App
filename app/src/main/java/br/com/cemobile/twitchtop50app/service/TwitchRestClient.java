package br.com.cemobile.twitchtop50app.service;

import java.util.Map;

import br.com.cemobile.twitchtop50app.Constant;
import br.com.cemobile.twitchtop50app.model.GamesListContent;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by celso on 28/05/16.
 */
public interface TwitchRestClient {

    @Headers(Constant.ACCEPT_HEADER)
    @GET(Constant.PATH_GAMES)
    public Call<GamesListContent> getTopGames();

    @Headers(Constant.ACCEPT_HEADER)
    @GET(Constant.PATH_GAMES)
    public Call<GamesListContent> getTopGames(@QueryMap Map<String, String> parameters);
}
