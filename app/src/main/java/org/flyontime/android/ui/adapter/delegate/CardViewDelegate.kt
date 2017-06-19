package org.flyontime.android.ui.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate

import org.flyontime.android.model.data.FlyOnTime.DashboardModelInterface
import org.flyontime.android.model.data.FlyOnTime.ItemModel
import org.flyontime.android.ui.adapter.CardViewHandlers
import org.flyontime.jw.android.R
import org.flyontime.jw.android.databinding.ViewRowBinding

/**
 * Created by jossi on 18.06.2017.
 */

class CardViewDelegate(private val inflater: LayoutInflater) : AdapterDelegate<List<DashboardModelInterface>>() {

    override fun isForViewType(items: List<DashboardModelInterface>, position: Int): Boolean {
        return items[position] is ItemModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ViewRowBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(items: List<DashboardModelInterface>, position: Int, holder: RecyclerView.ViewHolder, payloads: List<Any>) {
        val viewHolder = holder as CardViewHolder
        viewHolder.binding.school = items[position] as ItemModel
        viewHolder.binding.timeMarker.setMarker(viewHolder.binding.root.context.getDrawable(if ((items[position] as ItemModel).isActive) R.drawable.marker_outstanding else R.drawable.marker_done))
        viewHolder.binding.handlers = CardViewHandlers()
    }

    private inner class CardViewHolder internal constructor(internal val binding: ViewRowBinding) : RecyclerView.ViewHolder(binding.root)

}
