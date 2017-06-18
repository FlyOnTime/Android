package org.flyontime.android.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import org.flyontime.android.model.data.FlyOnTime.DashboardModelInterface;
import org.flyontime.android.model.data.FlyOnTime.DateModel;
import org.flyontime.android.model.data.FlyOnTime.ItemModel;
import org.flyontime.android.ui.adapter.delegate.CardViewDelegate;
import org.flyontime.android.ui.adapter.delegate.DateViewDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by jossi on 18.06.2017.
 */

public class DashboardAdapter extends RecyclerView.Adapter implements SearchAdapter {

    private AdapterDelegatesManager<List<DashboardModelInterface>> delegatesManager;
    private List<DashboardModelInterface> mItems = new ArrayList<>();

    private List<ItemModel> itemModels = new ArrayList<>();
    private List<ItemModel> originalItemModels = new ArrayList<>();
    private List<DateModel> dateModels = new ArrayList<>();

    public DashboardAdapter(LayoutInflater layoutInflater) {
        delegatesManager = new AdapterDelegatesManager<>();
        delegatesManager.addDelegate(new CardViewDelegate(layoutInflater));
        delegatesManager.addDelegate(new DateViewDelegate(layoutInflater));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(mItems, position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(mItems, position);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void add(DashboardModelInterface item) {
        mItems.add(item);
        notifyItemInserted(mItems.size());
        if (item instanceof ItemModel) {
            itemModels.add((ItemModel) item);
            originalItemModels.add((ItemModel) item);
        } else if (item instanceof DateModel) {
            dateModels.add((DateModel) item);
        }
    }

    public void insertAtPosition(int position, ItemModel item) {
        mItems.add(position, item);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ItemModel> filteredResults;
                if (constraint.length() == 0) {
                    filteredResults = itemModels;
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
                itemModels = (List<ItemModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public List<ItemModel> getFilteredResults(String constraint) {
        return SearchViewFilterStrategy.getFilteredResults(this.originalItemModels, constraint);
    }
}
