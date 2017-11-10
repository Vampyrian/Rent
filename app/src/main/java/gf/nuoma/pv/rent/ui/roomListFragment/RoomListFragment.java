package gf.nuoma.pv.rent.ui.roomListFragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.RoomListFragmentBinding;
import gf.nuoma.pv.rent.model.RoomModel;

public class RoomListFragment extends Fragment {

    private static final String LOG_TAG = "RoomListFragment";
    private RoomListFragmentBinding mBinding;
    private List<RoomModel> mRoomList;

    private FirebaseAuth mAuth;
    private DatabaseReference mOwnerListRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.room_list_fragment, container, false);
        mBinding.setRoomListFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        String uID = mAuth.getCurrentUser().getUid();
        mOwnerListRef = FirebaseDatabase.getInstance().getReference().child(getString(R.string.firebaseOwnerListRef)).child(uID);
        mOwnerListRef.addChildEventListener(childEventListener);
    }


    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Log.d(LOG_TAG, "Butas dadetas su ID: " + dataSnapshot.getKey());
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Log.d(LOG_TAG, "Butas pakeistas su ID: " + dataSnapshot.getKey());
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(LOG_TAG, "Butas istrintas su ID: " + dataSnapshot.getKey());
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            Log.d(LOG_TAG, "Butas pamovintas su ID: " + dataSnapshot.getKey());
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(LOG_TAG, "Kazkokia klaida su tekstu: " + databaseError.toException());
        }
    };

    /*
    ************************UI paspaudimu apdirbimas
    */

    public void onFabClicked (View v) {
        mOwnerListRef.push().setValue("mama");
    }

    interface OnAdapterRowClickListener {
        void onClick ();
    }
}
