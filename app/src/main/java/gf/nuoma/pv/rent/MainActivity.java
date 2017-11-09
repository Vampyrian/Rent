package gf.nuoma.pv.rent;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import gf.nuoma.pv.rent.databinding.MainActivityBinding;
import gf.nuoma.pv.rent.ui.calendarFragment.CalendarFragment;
import gf.nuoma.pv.rent.ui.requestFragment.RequestFragment;
import gf.nuoma.pv.rent.ui.signInFragment.SignInFragment;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private MainActivityBinding mBinding;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_calendar:
                    showCalendarFragment();
                    return true;
                case R.id.navigation_info:
                    return true;
                case R.id.navigation_request:
                    showRequestFragment();
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        BottomNavigationView navigation = mBinding.navigation;
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mAuth = FirebaseAuth.getInstance();
        FirebaseMessaging.getInstance().subscribeToTopic("newRent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            showSignInFragment();
        } else {
            showRequestFragment();
        }
    }

    //Tiesiog durnas apdirbimas. Tegu pabando uzdaryti klaviatura ir tiek
    private void hideKeyboard() {
        try {
            InputMethodManager imn = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    /*
    ****************************************Navigacija tarp fragmentu
     */

    public void showSignInFragment () {
        mBinding.setBottomNavigationVisible(false);

        SignInFragment fragment = new SignInFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();

        hideKeyboard();
    }

    public void showRequestFragment () {
        mBinding.setBottomNavigationVisible(true);

        RequestFragment fragment = new RequestFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();

        hideKeyboard();
    }

    public void showCalendarFragment () {
        CalendarFragment fragment = new CalendarFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();

        hideKeyboard();
    }

}
