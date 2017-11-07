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
    private DatabaseReference mDatabase;

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
        mDatabase = FirebaseDatabase.getInstance().getReference().child("requestList");
        mDatabase.addValueEventListener(listener);
    }

    private void write() {
        mDatabase.child("ehsbtb").setValue("abas");
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d(LOG_TAG, "Gavome duomenis");

            List<Request> requestList = new ArrayList<>();

            for(DataSnapshot data: dataSnapshot.getChildren()) {
                Request request = dataSnapshot.getValue(Request.class);
                requestList.add(request);
            }

            mAdapter.setRequestList(requestList);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };







    private void durniDuomenys() {
        Request request = new Request(20171212, 20, 300, 1);
        List<Request> list = new ArrayList<>();
        list.add(request);
        list.add(request);
        request.accept = 5;
        list.add(request);
        list.add(request);
        mAdapter.setRequestList(list);
    }




    /*
    *************************************UI apdirbimas
     */
    private final RequestAdapterCallback mCallback = new RequestAdapterCallback() {
        @Override
        public void onSelect(Request request) {
            //TODO Cia kazka padaryti kai paspaudi adapteryje
        }
    };

    public interface RequestAdapterCallback {
        void onSelect(Request request);
    }
}
