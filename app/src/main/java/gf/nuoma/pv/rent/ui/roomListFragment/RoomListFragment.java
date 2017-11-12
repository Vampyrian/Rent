package gf.nuoma.pv.rent.ui.roomListFragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.RoomListFragmentBinding;
import gf.nuoma.pv.rent.model.RoomModel;

public class RoomListFragment extends Fragment {

    private static final String LOG_TAG = "RoomListFragment";
    private RoomListFragmentBinding mBinding;
    private List<RoomModel> mRoomList;

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
