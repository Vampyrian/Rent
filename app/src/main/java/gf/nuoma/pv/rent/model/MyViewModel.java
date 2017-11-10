package gf.nuoma.pv.rent.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gf.nuoma.pv.rent.R;

public class MyViewModel extends AndroidViewModel {

    private static final String LOG_TAG = "MyViewMode";

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    public MyViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    public void init () {
        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child(getApplication().getString(R.string.firebaseRequestListRef));
    }
}
