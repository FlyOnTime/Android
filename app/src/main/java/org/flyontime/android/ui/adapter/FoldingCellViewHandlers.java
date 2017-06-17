package org.flyontime.android.ui.adapter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;

import org.flyontime.jw.android.R;

/**
 * Created by jossi on 17.06.2017.
 */

public class FoldingCellViewHandlers {

    private int originalHeightCard = -1;
    private boolean toggled = false;

    public void toggleExpand(View view) {
        CardView linearLayout = (CardView) view;
        //ConstraintLayout viewToPassOn = (ConstraintLayout) linearLayout.findViewById(R.id.expandingLayout);
        if (originalHeightCard == -1) {
            originalHeightCard = view.getHeight();
        }
        //animationDown(viewToPassOn, originalHeight);//here put the name of you layout that have the options to expand.
        if (toggled) {
            collapseView(linearLayout, originalHeightCard);
        } else {
            int newheight = originalHeightCard;
            expandView(linearLayout, newheight + 200);
        }
    }

    public void expandView(View cardView, int height) {

        ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(),
                height);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
            layoutParams.height = val;
            cardView.setLayoutParams(layoutParams);
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                cardView.findViewById(R.id.revealContent).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
        toggled = true;

    }

    public void collapseView(View cardView, int minHeight) {


        ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(),
                minHeight);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
            layoutParams.height = val;
            cardView.setLayoutParams(layoutParams);

        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                cardView.findViewById(R.id.revealContent).setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
        toggled = false;
    }

}
