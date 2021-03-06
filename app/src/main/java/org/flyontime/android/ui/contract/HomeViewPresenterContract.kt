package org.flyontime.android.ui.contract

import org.flyontime.android.base.BaseViewPresenterContract
import org.flyontime.android.model.state.HomeViewState

/**
 * Created by JW on 23.04.2017.
 */

interface HomeViewPresenterContract {

    interface HomeViewActions : BaseViewPresenterContract.BaseViewActions<HomeViewState> {
        override fun render(state: HomeViewState)
    }

    interface HomePresenterActions {
        fun goodRxloadSchools()
    }

}
