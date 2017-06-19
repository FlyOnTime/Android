package org.flyontime.android.model.data.FlyOnTime

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jossi on 18.06.2017.
 */

data class DateModel(val date: Date) : DashboardModelInterface() {

    val shortenedDateString: String
        get() {
            @SuppressLint("SimpleDateFormat")
            val simpleDateFormat = SimpleDateFormat("dd MMM hh:mm")
            return simpleDateFormat.format(date)
        }
}
