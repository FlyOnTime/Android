package org.flyontime.android.ui.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import org.flyontime.android.model.data.FlyOnTime.DashboardModelInterface
import org.flyontime.android.model.data.FlyOnTime.DateModel
import org.flyontime.jw.android.databinding.ViewRowDateBinding

/**
 * Created by jossi on 18.06.2017.
 */

class DateViewDelegate(private val inflater: LayoutInflater) : AdapterDelegate<List<DashboardModelInterface>>() {

    override fun isForViewType(items: List<DashboardModelInterface>, position: Int): Boolean {
        return items[position] is DateModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ViewRowDateBinding.inflate(inflater, parent, false)
        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(items: List<DashboardModelInterface>, position: Int, holder: RecyclerView.ViewHolder, payloads: List<Any>) {
        val viewHolder = holder as DateViewHolder
        viewHolder.binding.date = items[position] as DateModel
    }

    private inner class DateViewHolder internal constructor(internal val binding: ViewRowDateBinding) : RecyclerView.ViewHolder(binding.root)

}
