package gf.nuoma.pv.rent.ui.calendarFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.CalendarFragmentBinding;
import gf.nuoma.pv.rent.model.RequestModel;
import gf.nuoma.pv.rent.model.SharedViewModel;

public class CalendarFragment extends Fragment {

    private static final String LOG_TAG = "CalendarFragment";
    private CalendarFragmentBinding mBinding;
    private List<RequestModel> mRequestList = new ArrayList<>();
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getAllRequest().observe(this, new Observer<List<RequestModel>>() {
            @Override
            public void onChanged(@Nullable List<RequestModel> requests) {
                mRequestList = requests;
                selectDateInCalendar();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding.calendar.setOnDateChangedListener(null);
    }

    private void selectDateInCalendar() {
        mBinding.calendar.clearSelection();

        for(RequestModel request : mRequestList) {

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

            List<RequestModel> newList = new ArrayList<>();

            for(RequestModel request : mRequestList) {
                if (request.date.equals(selectedDate)) {
                    newList.add(request);
                }
            }
            mAdapter.setRequestList(newList);
        }
    };






}




