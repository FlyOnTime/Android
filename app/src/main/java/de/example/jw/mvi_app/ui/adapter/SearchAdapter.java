package de.example.jw.mvi_app.ui.adapter;

import android.widget.Filter;
import android.widget.Filterable;

import java.util.List;

import de.example.jw.mvi_app.model.data.SchoolsModel;

/**
 * Created by JW on 05.05.2017.
 */

public interface SearchAdapter extends Filterable {
    Filter getFilter();

    List<SchoolsModel> getFilteredResults(String constraint);
}
