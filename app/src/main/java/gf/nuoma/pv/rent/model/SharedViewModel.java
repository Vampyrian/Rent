package gf.nuoma.pv.rent.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import gf.nuoma.pv.rent.R;

public class SharedViewModel extends AndroidViewModel {

    private static final String LOG_TAG = "MyViewMode";
    private MutableLiveData<List<RequestModel>> mRequestList = new MutableLiveData<>();

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    public SharedViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    public void init () {
        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child(getApplication().getString(R.string.firebaseRequestListRef));
        mDatabaseRef.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDatabaseRef.removeEventListener(valueEventListener);
    }

    public MutableLiveData<List<RequestModel>> getAllRequest () {
        return mRequestList;
    }



    /*
    FIREBASE
     */
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d(LOG_TAG, "Gavome duomenis");

            List<RequestModel> requestList = new ArrayList<>();

            for(DataSnapshot data: dataSnapshot.getChildren()) {
                RequestModel request = data.getValue(RequestModel.class);
                request.key = data.getKey();
                requestList.add(request);
            }
            mRequestList.setValue(requestList);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(LOG_TAG, "Kazkas negerai. Isauktas onCanselled", databaseError.toException());
        }
    };
}
