package de.example.jw.mvi_app.ui.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.example.jw.mvi_app.model.data.SchoolsModel;

/**
 * Created by JW on 06.05.2017.
 */

public class SearchViewFilterStrategy {

    public static List<SchoolsModel> getFilteredResults(List<SchoolsModel> mOriginalItems, String constraint) {
        List<SchoolsModel> results = new ArrayList<>();
        for (SchoolsModel item : mOriginalItems) {
            if (item.getName().toLowerCase(Locale.ROOT).contains(constraint.toLowerCase(Locale.ROOT))) {
                results.add(item);
            }
        }
        return results;
    }

}
