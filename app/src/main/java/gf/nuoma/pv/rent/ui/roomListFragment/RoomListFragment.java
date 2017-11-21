package gf.nuoma.pv.rent.ui.roomListFragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.RoomListFragmentBinding;
import gf.nuoma.pv.rent.model.RoomModel;
import gf.nuoma.pv.rent.ui.base.BaseFragment;

public class RoomListFragment extends BaseFragment {

    private static final String LOG_TAG = RoomListFragment.class.getSimpleName();
    private RoomListFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.room_list_fragment, container, false);
        mBinding.setRoomListFragment(this);
        return mBinding.getRoot();
    }

    /*
    ************************UI paspaudimu apdirbimas
    */

    public void onFabClicked (View v) {
    }

    interface OnListRowClickListener {
        void onClick (RoomModel roomModel);
    }
}
