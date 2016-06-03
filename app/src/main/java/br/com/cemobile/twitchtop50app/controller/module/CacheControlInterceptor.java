package br.com.cemobile.twitchtop50app.controller.module;

import java.io.IOException;

import br.com.cemobile.twitchtop50app.Constant;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by celso on 02/06/16.
 */
public class CacheControlInterceptor implements Interceptor {

    private static CacheControlInterceptor instance;

    public CacheControlInterceptor() {}

    public static CacheControlInterceptor getInstance() {
        if (instance == null) {
            instance = new CacheControlInterceptor();
        }

        return instance;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // online response
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .header("Cache-Control", "public, max-age=" + Constant.MAX_AGE)
                .build();
        /*
            // offline response
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + Constant.MAX_STALE)
                    .build();
        */
    }
}
