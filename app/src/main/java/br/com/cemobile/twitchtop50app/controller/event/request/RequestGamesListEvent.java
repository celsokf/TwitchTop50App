package br.com.cemobile.twitchtop50app.controller.event.request;

import android.content.Context;

/**
 * Created by celso on 29/05/16.
 */
public class RequestGamesListEvent {

    private final Context context;

    private final int offset;

    public RequestGamesListEvent(Context context, int offset) {
        this.context = context;
        this.offset = offset;
    }

    public Context getContext() {
        return this.context;
    }

    public int getOffset() {
        return this.offset;
    }
}
