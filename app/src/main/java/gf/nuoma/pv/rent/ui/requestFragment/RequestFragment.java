package gf.nuoma.pv.rent.ui.requestFragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.RequestFragmentBinding;
import gf.nuoma.pv.rent.model.Request;

public class RequestFragment extends Fragment {
    private static final String LOG_TAG = "RequestFragment";
    private RequestFragmentBinding mBinding;
    private RequestAdapter mAdapter;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAdapter = new RequestAdapter(mCallback);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.request_fragment, container, false);
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child(getString(R.string.firebaseRequestListRef));
        mDatabaseRef.addValueEventListener(listener);
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d(LOG_TAG, "Gavome duomenis");

            List<Request> requestList = new ArrayList<>();

            for(DataSnapshot data: dataSnapshot.getChildren()) {
                Request request = data.getValue(Request.class);

                request.key = data.getKey();
                requestList.add(request);
            }
            mAdapter.setRequestList(requestList);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(LOG_TAG, "Kazkas negerai. Isauktas onCanselled", databaseError.toException());
        }
    };






    /*
    *************************************UI apdirbimas
     */
    private final RequestAdapterCallback mCallback = new RequestAdapterCallback() {
        @Override
        public void onAccept(Request request) {
            String email = mAuth.getCurrentUser().getEmail();
            request.accept = 0;
            request.owner = email;
            request.room = "Kambarys nr.1";
            mDatabaseRef.child(request.key).setValue(request);
        }
    };

    public interface RequestAdapterCallback {
        void onAccept(Request request);
    }
}
