package gf.nuoma.pv.rent.db.network;

import android.content.Context;
import android.content.Intent;
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
        Log.d(LOG_TAG, "Paprase instanco is " + Thread.currentThread().getName());
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NetworkDataSource(context.getApplicationContext(), executor);
                Log.d(LOG_TAG, "Pirma syki sukurem instanca is " + Thread.currentThread().getName());
            }
        }
        return sInstance;
    }

    //Pradedame pumpuoti duomenis
    public void startMyIntentService () {
        Intent intent = new Intent(mContext, MyIntentService.class);
        mContext.startService(intent);
        Log.d(LOG_TAG, "Iskvieteme servisa is " + Thread.currentThread().getName());
    }

    public void fetchData() {
        Log.d(LOG_TAG, "Pasileido fetchData is " + Thread.currentThread().getName());

        mExecutor.getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }




}
