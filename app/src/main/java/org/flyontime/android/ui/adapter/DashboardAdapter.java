package org.flyontime.android.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import org.flyontime.android.model.data.DashboardModelInterface;
import org.flyontime.android.model.data.ItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jossi on 18.06.2017.
 */

public class DashboardAdapter extends RecyclerView.Adapter {

    private AdapterDelegatesManager<List<DashboardModelInterface>> delegatesManager;
    private List<DashboardModelInterface> mItems = new ArrayList<>();

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
    }

    public void insertAtPosition(int position, ItemModel item) {
        mItems.add(position, item);
    }
}
