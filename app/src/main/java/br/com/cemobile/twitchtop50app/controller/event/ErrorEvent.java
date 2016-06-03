package br.com.cemobile.twitchtop50app.controller.event;

/**
 * Created by celso on 28/05/16.
 */
public class ErrorEvent {

    private final String message;

    public ErrorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
