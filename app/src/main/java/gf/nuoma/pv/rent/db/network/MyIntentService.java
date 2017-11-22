package gf.nuoma.pv.rent.db.network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import gf.nuoma.pv.rent.util.InjectorUtil;

//Sitas servizas tiesiog nuskaito duomenis is interneto kai pasileidzia
public class MyIntentService extends IntentService {

    private static final String LOG_TAG = MyIntentService.class.getSimpleName();

    public MyIntentService () {
        super("ManoIntentServisas");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "Pasileido onHandleIntent is " + Thread.currentThread().getName());
        NetworkDataSource networkDataSource = InjectorUtil.provideNetworkDataSource(this.getApplicationContext());
        networkDataSource.fetchData();
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "Servisas iskviete onDestroy");
        super.onDestroy();
    }
}
