package br.com.cemobile.twitchtop50app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by celso on 28/05/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopGame implements Serializable {

    @JsonProperty("viewers")
    private long viewers;

    @JsonProperty("channels")
    private int channels;

    @JsonProperty("game")
    private Game game;

    private int position;

    public long getViewers() {
        return viewers;
    }

    public void setViewers(long viewers) {
        this.viewers = viewers;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
