package org.flyontime.android.ui.animation

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import org.flyontime.jw.android.R

/**
 * Created by jossi on 18.06.2017.
 */

object AnimationUtils {

    fun expandView(cardView: View, height: Int) {

        val anim = ValueAnimator.ofInt(cardView.measuredHeightAndState,
                height)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = cardView.layoutParams
            layoutParams.height = `val`
            cardView.layoutParams = layoutParams
        }
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                cardView.findViewById(R.id.revealContent).visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
        anim.start()

    }

    fun collapseView(cardView: View, minHeight: Int) {

        val anim = ValueAnimator.ofInt(cardView.measuredHeightAndState,
                minHeight)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = cardView.layoutParams
            layoutParams.height = `val`
            cardView.layoutParams = layoutParams

        }
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                cardView.findViewById(R.id.revealContent).visibility = View.GONE
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
        anim.start()
    }

}
