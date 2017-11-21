package gf.nuoma.pv.rent.ui.base;

import android.support.v4.app.FragmentManager;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.ui.calendarFragment.CalendarFragment;
import gf.nuoma.pv.rent.ui.requestListFragment.RequestListFragment;
import gf.nuoma.pv.rent.ui.roomListFragment.RoomListFragment;
import gf.nuoma.pv.rent.ui.signInFragment.SignInFragment;

public abstract class NavigationBetweenFragmentActivity extends BaseActivity {

    @Override
    public boolean onSupportNavigateUp() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int backStackSize = fragmentManager.getBackStackEntryCount();
        if (backStackSize > 0) {
            fragmentManager.popBackStack();
        } else {
            showRequestFragment();
        }
        return true;
    }




    /*
    ****************************************Navigacija tarp fragmentu
     */

    public void showSignInFragment () {
        SignInFragment fragment = new SignInFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();

        hideKeyboard();
    }

    public void showRequestFragment () {
        RequestListFragment fragment = new RequestListFragment();
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

    public void showRoomListFragment () {
        RoomListFragment fragment = new RoomListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();

        hideKeyboard();
    }


}
