package gf.nuoma.pv.rent.db;

import android.util.Log;

import gf.nuoma.pv.rent.excutor.AppExecutor;

public class Repository {

    private static final String LOG_TAG = Repository.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static Repository sInstance;
    private final AppExecutor mAppExecutor;

    private Repository (AppExecutor appExecutor) {
        mAppExecutor = appExecutor;
    }

    public synchronized static Repository getInstance (AppExecutor appExecutor) {
        Log.d(LOG_TAG, "Uzprase repositorijos is " + Thread.currentThread().getName());
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Repository(appExecutor);
                Log.d(LOG_TAG, "Padareme nauja Repositorija is " + Thread.currentThread().getName());
            }
        }
        return sInstance;
    }
}
