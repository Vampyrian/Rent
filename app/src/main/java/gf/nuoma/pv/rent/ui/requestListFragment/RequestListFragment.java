package gf.nuoma.pv.rent.ui.requestListFragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.RequestListFragmentBinding;
import gf.nuoma.pv.rent.model.RequestModel;
import gf.nuoma.pv.rent.ui.base.BaseFragment;

public class RequestListFragment extends BaseFragment {

    private static final String LOG_TAG = RequestListFragment.class.getSimpleName();
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

    }

    /*
    *************************************UI apdirbimas
     */
    OnListRowClickListener mCallback = new OnListRowClickListener() {
        @Override
        public void onRowClick(RequestModel requestModel) {

        }
    };

    public interface OnListRowClickListener {
        void onRowClick (RequestModel requestModel);
    }

}
