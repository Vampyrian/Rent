package gf.nuoma.pv.rent.ui.requestListFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.RequestListFragmentBinding;
import gf.nuoma.pv.rent.model.RequestModel;
import gf.nuoma.pv.rent.model.SharedViewModel;

public class RequestListFragment extends Fragment {
    private static final String LOG_TAG = "RequestListFragment";
    private RequestListFragmentBinding mBinding;
    private RequestAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAdapter = new RequestAdapter(mCallback);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.request_list_fragment, container, false);
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
                mAdapter.setRequestList(requests);
            }
        });
    }

    /*
    *************************************UI apdirbimas
     */
    OnListRowClickListener mCallback = new OnListRowClickListener() {
        @Override
        public void onRowClick(RequestModel requestModel) {
            int a = 7;
            a = 8;
        }
    };

    public interface OnListRowClickListener {
        void onRowClick (RequestModel requestModel);
    }

}
