package org.flyontime.android.ui.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import org.flyontime.android.model.data.FlyOnTime.DashboardModelInterface;
import org.flyontime.android.model.data.FlyOnTime.ItemModel;
import org.flyontime.android.ui.adapter.CardViewHandlers;
import org.flyontime.jw.android.R;
import org.flyontime.jw.android.databinding.ViewRowBinding;

import java.util.List;

/**
 * Created by jossi on 18.06.2017.
 */

public class CardViewDelegate extends AdapterDelegate<List<DashboardModelInterface>> {

    private LayoutInflater inflater;

    public CardViewDelegate(LayoutInflater inflater) {
        this.inflater = inflater;
    }


    @Override
    protected boolean isForViewType(@NonNull List<DashboardModelInterface> items, int position) {
        return items.get(position) instanceof ItemModel;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewRowBinding binding = ViewRowBinding.inflate(inflater, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<DashboardModelInterface> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        CardViewHolder viewHolder = (CardViewHolder) holder;
        viewHolder.binding.setSchool((ItemModel) items.get(position));
        viewHolder.binding.timeMarker.setMarker((viewHolder.binding.getRoot().getContext().getDrawable(((ItemModel) items.get(position)).isActive() ? R.drawable.marker_outstanding : R.drawable.marker_done)));
        viewHolder.binding.setHandlers(new CardViewHandlers());
    }

    private class CardViewHolder extends RecyclerView.ViewHolder {

        private ViewRowBinding binding;

        CardViewHolder(ViewRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            //name = (TextView) itemView.findViewById(R.id.name);
        }
    }

}
