package gf.nuoma.pv.rent.ui.calendarFragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.CalendarFragmentBinding;
import gf.nuoma.pv.rent.model.Request;

public class CalendarFragment extends Fragment {

    private static final String LOG_TAG = "CalendarFragment";
    private CalendarFragmentBinding mBinding;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;
    private List<Request> mRequestList = new ArrayList<>();
    private CalendarAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAdapter = new CalendarAdapter();

        mBinding = DataBindingUtil.inflate(inflater, R.layout.calendar_fragment, container, false);
        mBinding.calendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        mBinding.calendar.setOnDateChangedListener(dateSelectedListener);
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

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
                    //Surasome tik tas datas kuriu savininkai esame mes
                    String email = mAuth.getCurrentUser().getEmail();
                    if (Objects.equals(email, request.owner)) {
                        mRequestList.add(request);
                    }
                }
                selectDateInCalendar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(LOG_TAG, "Kazkas negerai. Isauktas onCanselled", databaseError.toException());
            }
        });
    }

    private void selectDateInCalendar() {
        mBinding.calendar.clearSelection();

        for(Request request : mRequestList) {
            String year = request.date.substring(0, 4);
            String month = request.date.substring(5,7);
            String day = request.date.substring(8,10);

            int intYear = Integer.parseInt(year);
            int intMonth = Integer.parseInt(month) - 1;
            int intDay = Integer.parseInt(day);

            CalendarDay selectedDay = CalendarDay.from(intYear, intMonth, intDay);

            mBinding.calendar.setDateSelected(selectedDay, true);
        }
    }

    /*
         Navigacija is UI
    */

    private OnDateSelectedListener dateSelectedListener = new OnDateSelectedListener() {
        @Override
        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
            selectDateInCalendar();

            //Irasome i adapteri dienas ar yra uzimtu kambariu esama diena
            int year = date.getYear();
            int month = date.getMonth();
            int day = date.getDay();
            String stringYear = String.valueOf(year);
            String stringMonth;
            String stringDay;
            if (month < 10) {
                stringMonth = "0" + String.valueOf(month+1);
            } else {
                stringMonth = String.valueOf(month+1);
            }
            if (day < 10) {
                stringDay = "0" + String.valueOf(day);
            } else {
                stringDay = String.valueOf(day);
            }

            String selectedDate = stringYear + "." + stringMonth + "." + stringDay;

            List<Request> newList = new ArrayList<>();

            for(Request request : mRequestList) {
                if (request.date.equals(selectedDate)) {
                    newList.add(request);
                }
            }
            mAdapter.setRequestList(newList);
        }
    };






}




