package org.flyontime.android.ui.adapter;

import org.flyontime.android.model.data.ItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by JW on 06.05.2017.
 */

public class SearchViewFilterStrategy {

    public static List<ItemModel> getFilteredResults(List<ItemModel> mOriginalItems, String constraint) {
        List<ItemModel> results = new ArrayList<>();
        for (ItemModel item : mOriginalItems) {
            if (item.getCardTitle().toLowerCase(Locale.ROOT).contains(constraint.toLowerCase(Locale.ROOT))) {
                results.add(item);
            }
        }
        return results;
    }

}
