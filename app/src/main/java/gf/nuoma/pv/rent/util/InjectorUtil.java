package gf.nuoma.pv.rent.util;

import android.content.Context;

import gf.nuoma.pv.rent.db.Repository;
import gf.nuoma.pv.rent.excutor.AppExecutor;
import gf.nuoma.pv.rent.model.SharedViewModelFactory;

public class InjectorUtil {
    private InjectorUtil () {}

    public static SharedViewModelFactory provideSharedViewModelFactory (Context context) {
        Repository repository = provideRepository(context);
        return new SharedViewModelFactory(repository);
    }

    public static Repository provideRepository (Context context) {
//        AppDatabase roomDb = AppDatabase.getInstance(context);
        AppExecutor appExecutor = AppExecutor.getInstance();
        return Repository.getInstance(appExecutor);
    }
}
