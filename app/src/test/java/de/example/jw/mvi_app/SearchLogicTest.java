package de.example.jw.mvi_app;

import android.annotation.SuppressLint;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.List;

import de.example.jw.mvi_app.model.data.SchoolsModel;
import de.example.jw.mvi_app.ui.adapter.SearchViewFilterStrategy;

import static de.example.jw.mvi_app.MockUtils.fakeSchool;
import static de.example.jw.mvi_app.MockUtils.generateFakeList;
import static org.junit.Assert.assertTrue;

/**
 * Created by JW on 06.05.2017.
 */

public class SearchLogicTest {

    @SuppressLint("Assert")
    @Test
    public void searchResultsCorrect() {
        String schoolName = "Heilwig Gymnasium";

        List<SchoolsModel> originalItems = generateFakeList();
        originalItems.add(fakeSchool("Corvey Gymnasium"));
        originalItems.add(fakeSchool("Stadtteilschule Alter Teichweg"));

        List<SchoolsModel> expectedResults = generateFakeList();
        List<SchoolsModel> realResults = SearchViewFilterStrategy.getFilteredResults(originalItems, schoolName);

        assertTrue(CollectionUtils.isEqualCollection(expectedResults, realResults));

    }

}
