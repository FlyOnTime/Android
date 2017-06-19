package org.flyontime.android.base

/**
 * Created by JW on 25.04.2017.
 */

interface BaseViewPresenterContract {

    interface BaseViewActions<T> {
        fun render(state: T)
    }

}