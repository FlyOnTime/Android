package org.flyontime.android.ui.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import org.flyontime.android.model.data.FlyOnTime.DashboardModelInterface;
import org.flyontime.android.model.data.FlyOnTime.DateModel;
import org.flyontime.jw.android.databinding.ViewRowDateBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jossi on 18.06.2017.
 */

public class DateViewDelegate extends AdapterDelegate<List<DashboardModelInterface>> {

    private LayoutInflater inflater;

    private List<DateModel> mItems = new ArrayList<>();

    public DateViewDelegate(LayoutInflater inflater) {
        this.inflater = inflater;
    }


    @Override
    protected boolean isForViewType(@NonNull List<DashboardModelInterface> items, int position) {
        return items.get(position) instanceof DateModel;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewRowDateBinding binding = ViewRowDateBinding.inflate(inflater, parent, false);
        return new DateViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<DashboardModelInterface> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        DateViewHolder viewHolder = (DateViewHolder) holder;
        viewHolder.binding.setDate((DateModel) items.get(position));
    }

    private class DateViewHolder extends RecyclerView.ViewHolder {

        private ViewRowDateBinding binding;

        DateViewHolder(ViewRowDateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            //name = (TextView) itemView.findViewById(R.id.name);
        }
    }

}
