package de.example.jw.mvi_app.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.example.jw.mvi_app.databinding.ViewRowBinding;
import de.example.jw.mvi_app.model.data.SchoolsModel;

/**
 * Created by JW on 12.10.2016.
 */

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsAdapter.SchoolsViewHolder> implements SearchAdapter {

    private List<SchoolsModel> mItems = new ArrayList<>();
    private List<SchoolsModel> mOriginalItems = new ArrayList<>();

    private LayoutInflater layoutInflater;

    public SchoolsAdapter(LayoutInflater inflater) {
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
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void add(SchoolsModel item) {
        mItems.add(item);
        mOriginalItems.add(item);
        notifyItemInserted(mItems.size());
    }

    public void insertAtPosition(int position, SchoolsModel item) {
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
                List<SchoolsModel> filteredResults;
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
                mItems = (List<SchoolsModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public List<SchoolsModel> getFilteredResults(String constraint) {
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