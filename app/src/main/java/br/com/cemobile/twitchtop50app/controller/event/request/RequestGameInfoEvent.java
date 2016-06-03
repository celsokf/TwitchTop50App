package br.com.cemobile.twitchtop50app.controller.event.request;

import android.content.Context;

/**
 * Created by celso on 29/05/16.
 */
public class RequestGameInfoEvent {

    private Context context;

    private String path;

    public RequestGameInfoEvent(Context context, String path) {
        this.context = context;
        this.path = path;
    }

    public Context getContext() {
        return this.context;
    }

    public String getPath() {
        return this.path;
    }

}
