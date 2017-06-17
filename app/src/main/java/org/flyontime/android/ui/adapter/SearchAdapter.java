package org.flyontime.android.ui.adapter;

import android.widget.Filter;
import android.widget.Filterable;

import org.flyontime.android.model.data.ItemModel;

import java.util.List;

/**
 * Created by JW on 05.05.2017.
 */

public interface SearchAdapter extends Filterable {
    Filter getFilter();

    List<ItemModel> getFilteredResults(String constraint);
}
