package gf.nuoma.pv.rent.ui.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import gf.nuoma.pv.rent.MainActivity;
import gf.nuoma.pv.rent.model.SharedViewModel;
import gf.nuoma.pv.rent.model.SharedViewModelFactory;
import gf.nuoma.pv.rent.util.InjectorUtil;

public abstract class BaseFragment extends Fragment {

    private static final String LOG_TAG = BaseFragment.class.getSimpleName();
    protected SharedViewModel sharedViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedViewModelFactory factory= InjectorUtil.provideSharedViewModelFactory(getContext());
        sharedViewModel = ViewModelProviders.of(getActivity(),factory).get(SharedViewModel.class);
    }

    protected void hideKeyboard () {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).hideKeyboard();
        }
    }
}
