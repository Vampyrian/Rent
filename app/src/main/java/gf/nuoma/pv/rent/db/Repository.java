package gf.nuoma.pv.rent.db;

import android.util.Log;

import gf.nuoma.pv.rent.db.network.NetworkDataSource;
import gf.nuoma.pv.rent.excutor.AppExecutor;

public class Repository {

    private static final String LOG_TAG = Repository.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static Repository sInstance;
    private final AppExecutor mAppExecutor;
    private final NetworkDataSource mNetworkDataSource;

    private Repository (AppExecutor appExecutor, NetworkDataSource networkDataSource) {
        mAppExecutor = appExecutor;
        mNetworkDataSource = networkDataSource;
    }

    public synchronized static Repository getInstance (AppExecutor appExecutor, NetworkDataSource networkDataSource) {
        Log.d(LOG_TAG, "Uzprase repositorijos is " + Thread.currentThread().getName());
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Repository(appExecutor, networkDataSource);
                Log.d(LOG_TAG, "Pirma syki padareme nauja Repositorija is " + Thread.currentThread().getName());
            }
        }
        return sInstance;
    }

    public void paleiskServisa () {
        startFetchService();
    }

    private void startFetchService () {
        mNetworkDataSource.startMyIntentService();
    }
}
