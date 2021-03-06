package gf.nuoma.pv.rent.util;

import android.content.Context;

import gf.nuoma.pv.rent.db.Repository;
import gf.nuoma.pv.rent.db.network.NetworkDataSource;
import gf.nuoma.pv.rent.excutor.AppExecutor;
import gf.nuoma.pv.rent.model.SharedViewModelFactory;

public class InjectorUtil {
    private InjectorUtil () {}

    public static SharedViewModelFactory provideSharedViewModelFactory (Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new SharedViewModelFactory(repository);
    }

    public static Repository provideRepository (Context context) {
//        AppDatabase roomDb = AppDatabase.getInstance(context);
        AppExecutor appExecutor = AppExecutor.getInstance();
        NetworkDataSource networkDataSource = NetworkDataSource.getInstance(context.getApplicationContext(), appExecutor);

        return Repository.getInstance(appExecutor, networkDataSource);
    }

    public static NetworkDataSource provideNetworkDataSource (Context context) {
        AppExecutor appExecutor = AppExecutor.getInstance();
        //Sita reikia iskviesti nes jeigu appsas pasileis is serviso tai repositorijos nebus
        provideRepository(context.getApplicationContext());
        return NetworkDataSource.getInstance(context.getApplicationContext(), appExecutor);
    }
}
