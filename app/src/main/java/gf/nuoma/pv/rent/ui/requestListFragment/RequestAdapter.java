package gf.nuoma.pv.rent.ui.requestListFragment;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.RequestListRowBinding;
import gf.nuoma.pv.rent.model.RequestModel;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private RequestListFragment.OnListRowClickListener mCallback;
    private List<RequestModel> mRequestList;

    RequestAdapter (RequestListFragment.OnListRowClickListener callback) {
        mCallback = callback;
    }

    public void setRequestList (final List<RequestModel> newRequestList){
        if (mRequestList == null) {
            mRequestList = newRequestList;
            notifyItemRangeInserted(0, newRequestList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mRequestList.size();
                }

                @Override
                public int getNewListSize() {
                    return newRequestList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return true;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    RequestModel newRequest = newRequestList.get(newItemPosition);
                    RequestModel oldRequest = mRequestList.get(oldItemPosition);
                    return newRequest.accept == oldRequest.accept &&
                            newRequest.price == oldRequest.price &&
                            newRequest.count == oldRequest.count &&
                            Objects.equals(newRequest.owner, oldRequest.owner) &&
                            Objects.equals(newRequest.room, oldRequest.room) &&
                            Objects.equals(newRequest.date, oldRequest.date);
                }
            });
            mRequestList = newRequestList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RequestListRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.request_list_row,
                parent,
                false);
        binding.setCallback(mCallback);
        return new RequestViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        holder.mBinding.setRequest(mRequestList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRequestList == null ? 0 : mRequestList.size();
    }




    static class RequestViewHolder extends RecyclerView.ViewHolder {
        final RequestListRowBinding mBinding;

        RequestViewHolder (RequestListRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }


}
