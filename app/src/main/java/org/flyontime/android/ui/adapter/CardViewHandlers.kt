package org.flyontime.android.ui.adapter

import android.support.v7.widget.CardView
import android.view.View

import org.flyontime.android.ui.animation.AnimationUtils.collapseView
import org.flyontime.android.ui.animation.AnimationUtils.expandView

/**
 * Created by jossi on 17.06.2017.
 */

class CardViewHandlers {

    private var originalViewHeight = -1
    private var toggled = false

    fun toggleExpand(view: View) {
        val cardView = view as CardView
        if (originalViewHeight == -1) {
            originalViewHeight = view.getHeight()
        }
        if (toggled) {
            collapseView(cardView, originalViewHeight)
            toggled = false
        } else {
            expandView(cardView, originalViewHeight + 200)
            toggled = true
        }
    }


}
