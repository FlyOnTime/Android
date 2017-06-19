package org.flyontime.android.ui.adapter

import org.flyontime.android.model.data.FlyOnTime.ItemModel
import java.util.*

/**
 * Created by JW on 06.05.2017.
 */

object SearchViewFilterStrategy {

    fun getFilteredResults(mOriginalItems: List<ItemModel>, constraint: String): List<ItemModel> {
        val results = ArrayList<ItemModel>()
        for (item in mOriginalItems) {
            if (item.cardTitle.toLowerCase(Locale.ROOT).contains(constraint.toLowerCase(Locale.ROOT))) {
                results.add(item)
            }
        }
        return results
    }

}
