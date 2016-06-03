package br.com.cemobile.twitchtop50app.controller.component;

import javax.inject.Singleton;

import br.com.cemobile.twitchtop50app.controller.module.TwitchModule;
import br.com.cemobile.twitchtop50app.service.TwitchRestClient;
import dagger.Component;

/**
 * Created by celso on 29/05/16.
 */

@Singleton
@Component(modules = {TwitchModule.class})
public interface TwitchComponent {

    public TwitchRestClient provideTwitchRestClient();

}
