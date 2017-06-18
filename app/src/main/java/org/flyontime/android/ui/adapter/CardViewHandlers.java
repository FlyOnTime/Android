package org.flyontime.android.ui.adapter;

import android.support.v7.widget.CardView;
import android.view.View;

import static org.flyontime.android.ui.animation.AnimationUtils.collapseView;
import static org.flyontime.android.ui.animation.AnimationUtils.expandView;

/**
 * Created by jossi on 17.06.2017.
 */

public class CardViewHandlers {

    private int originalViewHeight = -1;
    private boolean toggled = false;

    public void toggleExpand(View view) {
        CardView cardView = (CardView) view;
        if (originalViewHeight == -1) {
            originalViewHeight = view.getHeight();
        }
        if (toggled) {
            collapseView(cardView, originalViewHeight);
            toggled = false;
        } else {
            expandView(cardView, originalViewHeight + 200);
            toggled = true;
        }
    }


}
