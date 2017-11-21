package gf.nuoma.pv.rent.db.network;

import android.content.Context;
import android.util.Log;

import gf.nuoma.pv.rent.excutor.AppExecutor;

public class NetworkDataSource {

    private static final String LOG_TAG = NetworkDataSource.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static NetworkDataSource sInstance;
    private final Context mContext;

    private final AppExecutor mExecutor;

    private NetworkDataSource (Context context, AppExecutor executor) {
        mContext = context;
        mExecutor = executor;
    }

    public static NetworkDataSource getInstance (Context context, AppExecutor executor) {
        Log.d(LOG_TAG, "Kazkas paprase instanco is " + Thread.currentThread().getName());
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NetworkDataSource(context, executor);
                Log.d(LOG_TAG, "Pirma syki sukurem instanca is " + Thread.currentThread().getName());
            }
        }
        return sInstance;
    }




}
