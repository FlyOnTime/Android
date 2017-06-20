package org.flyontime.android.ui.contract

import org.flyontime.android.base.BaseViewPresenterContract
import org.flyontime.android.model.state.FetchEmailsViewState

/**
 * Created by JW on 23.04.2017.
 */

interface FetchEmailsViewPresenterContract {

    interface FetchEmailsViewActions : BaseViewPresenterContract.BaseViewActions<FetchEmailsViewState> {
        override fun render(state: FetchEmailsViewState)
    }

    interface FetchEmailsPresenterActions {
        fun searchMessages()
    }

}
