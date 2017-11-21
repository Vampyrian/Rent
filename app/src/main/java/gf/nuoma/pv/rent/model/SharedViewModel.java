package gf.nuoma.pv.rent.model;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import gf.nuoma.pv.rent.db.Repository;

public class SharedViewModel extends ViewModel {

    private static final String LOG_TAG = SharedViewModel.class.getSimpleName();
    private Repository mRepository;

    public SharedViewModel(Repository repository) {
        Log.d(LOG_TAG, "Sukuriam SharedViewModel");
        mRepository = repository;
    }


}
