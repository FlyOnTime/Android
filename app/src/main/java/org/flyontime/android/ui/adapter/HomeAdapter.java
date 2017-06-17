package org.flyontime.android.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;

import org.flyontime.android.model.data.ItemModel;
import org.flyontime.jw.android.R;
import org.flyontime.jw.android.databinding.ViewRowBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by JW on 12.10.2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.SchoolsViewHolder> implements SearchAdapter {

    private List<ItemModel> mItems = new ArrayList<>();
    private List<ItemModel> mOriginalItems = new ArrayList<>();

    private LayoutInflater layoutInflater;


    public HomeAdapter(LayoutInflater inflater) {
        this.layoutInflater = inflater;
    }

    @Override
    public SchoolsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewRowBinding binding = ViewRowBinding.inflate(layoutInflater, parent, false);
        return new SchoolsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SchoolsViewHolder holder, int position) {
        holder.getBinding().setSchool(mItems.get(position));
        holder.getBinding().timeMarker.setMarker(holder.getBinding().getRoot().getContext().getDrawable(mItems.get(position).isActive() ? R.drawable.marker_outstanding : R.drawable.marker_done));
        holder.getBinding().setHandlers(new FoldingCellViewHandlers());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void add(ItemModel item) {
        mItems.add(item);
        mOriginalItems.add(item);
        notifyItemInserted(mItems.size());
    }

    public void insertAtPosition(int position, ItemModel item) {
        mItems.add(position, item);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ItemModel> filteredResults;
                if (constraint.length() == 0) {
                    filteredResults = mItems;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase(Locale.ROOT));
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }

            @Override
            @SuppressWarnings("unchecked")
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mItems = (List<ItemModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public List<ItemModel> getFilteredResults(String constraint) {
        return SearchViewFilterStrategy.getFilteredResults(this.mOriginalItems, constraint);
    }

    class SchoolsViewHolder extends RecyclerView.ViewHolder {

        private ViewRowBinding binding;

        SchoolsViewHolder(ViewRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        ViewRowBinding getBinding() {
            return binding;
        }
    }
}