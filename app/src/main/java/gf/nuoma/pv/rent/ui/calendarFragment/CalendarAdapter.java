package gf.nuoma.pv.rent.ui.calendarFragment;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import gf.nuoma.pv.rent.R;
import gf.nuoma.pv.rent.databinding.CalendarListRowBinding;
import gf.nuoma.pv.rent.model.Request;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarRequestViewHolder> {

    private List<Request> mRequestList;

    public void setRequestList (final List<Request> newRequestList){
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
                    Request newRequest = newRequestList.get(newItemPosition);
                    Request oldRequest = mRequestList.get(oldItemPosition);
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
    public CalendarRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CalendarListRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.calendar_list_row,
                parent,
                false);
        return new CalendarRequestViewHolder (binding);
    }

    @Override
    public void onBindViewHolder(CalendarRequestViewHolder holder, int position) {
        holder.mBinding.setRequest(mRequestList.get(position));
//        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mRequestList == null ? 0 : mRequestList.size();
    }

    static class CalendarRequestViewHolder extends RecyclerView.ViewHolder {
        final CalendarListRowBinding mBinding;

        CalendarRequestViewHolder (CalendarListRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }


}

