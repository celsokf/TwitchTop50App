package br.com.cemobile.twitchtop50app.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import de.greenrobot.event.EventBus;

/**
 * Created by celso on 28/05/16.
 */
public class GenericUtil {

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

}
