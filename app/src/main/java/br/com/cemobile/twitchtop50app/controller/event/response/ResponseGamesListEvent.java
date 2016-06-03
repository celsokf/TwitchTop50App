package br.com.cemobile.twitchtop50app.controller.event.response;

import br.com.cemobile.twitchtop50app.model.GamesListContent;

/**
 * Created by celso on 29/05/16.
 */
public class ResponseGamesListEvent {

    private int offset;

    private final GamesListContent gamesListContent;

    public ResponseGamesListEvent(GamesListContent gamesListContent, int offset) {
        this.gamesListContent = gamesListContent;
        this.offset = offset;
    }

    public GamesListContent getGamesListContent() {
        return this.gamesListContent;
    }

    public int getOffset() {
        return this.offset;
    }
}
