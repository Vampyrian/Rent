package gf.nuoma.pv.rent.ui.calendarFragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.CalendarFragmentBinding;
import gf.nuoma.pv.rent.model.Request;

public class CalendarFragment extends Fragment {

    private static final String LOG_TAG = "CalendarFragment";
    private CalendarFragmentBinding mBinding;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;
    private List<Request> mRequestList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.calendar_fragment, container, false);
        mBinding.calendarView.setOnDateChangeListener(dataChangeListener);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child(getString(R.string.firebaseRequestListRef));
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(LOG_TAG, "Gavome duomenis");

                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    Request request = data.getValue(Request.class);
                    request.key = data.getKey();
                    mRequestList.add(request);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(LOG_TAG, "Kazkas negerai. Isauktas onCanselled", databaseError.toException());
            }
        });
    }

    /*
         Navigacija is UI
    */

    private CalendarView.OnDateChangeListener dataChangeListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            long data = mBinding.calendarView.getDate();

            //mBinding.calendarView.setDate(data);
            int b = 8;
            b = b + 2;
            


        }
    };




}




