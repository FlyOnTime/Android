package org.flyontime.android.ui.adapter

import android.widget.Filter
import android.widget.Filterable

import org.flyontime.android.model.data.FlyOnTime.ItemModel

/**
 * Created by JW on 05.05.2017.
 */

interface SearchAdapter : Filterable {
    override fun getFilter(): Filter

    fun getFilteredResults(constraint: String): List<ItemModel>
}
