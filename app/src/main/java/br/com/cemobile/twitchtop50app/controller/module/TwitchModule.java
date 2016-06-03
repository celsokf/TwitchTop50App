package br.com.cemobile.twitchtop50app.controller.module;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import br.com.cemobile.twitchtop50app.Constant;
import br.com.cemobile.twitchtop50app.service.TwitchRestClient;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by celso on 29/05/16.
 */

@Module
public class TwitchModule {

    @Provides
    @Singleton
    TwitchRestClient provideTwitchRestClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
/*
        // TODO implementar o tratamento offline usando cache em disco atraves do OkHttpClient
        // handle cache response
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, Constant.CACHE_SIZE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(CacheControlInterceptor.getInstance())
                .cache(cache)
                .build();
*/
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Executor executor = Executors.newCachedThreadPool();

        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .callbackExecutor(executor)
                .client(client)
                .build()
                .create(TwitchRestClient.class);
    }


}
