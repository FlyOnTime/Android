package org.flyontime.android.base

/**
 * Created by JW on 29.04.2017.
 */

interface BaseViewState<S> {
    fun reduce(previous: S): S
}
