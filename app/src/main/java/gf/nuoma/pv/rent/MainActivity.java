package gf.nuoma.pv.rent;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import gf.nuoma.pv.rent.databinding.MainActivityBinding;
import gf.nuoma.pv.rent.ui.base.NavigationBetweenFragmentActivity;

public class MainActivity extends NavigationBetweenFragmentActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private MainActivityBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        BottomNavigationView navigation = mBinding.navigation;
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_calendar:
                        showCalendarFragment();
                        return true;
                    case R.id.navigation_info:
                        showRoomListFragment();
                        return true;
                    case R.id.navigation_request:
                        showRequestFragment();
                        return true;
                    case R.id.navigation_settings:
                        return true;
                }
                return false;
            }
        });

        showRequestFragment();
    }



}
