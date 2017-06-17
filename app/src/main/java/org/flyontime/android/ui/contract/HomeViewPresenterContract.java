package org.flyontime.android.ui.contract;

import org.flyontime.android.base.BaseViewPresenterContract;
import org.flyontime.android.model.state.HomeViewState;

/**
 * Created by JW on 23.04.2017.
 */

public interface HomeViewPresenterContract {

    interface SchoolsViewActions extends BaseViewPresenterContract.BaseViewActions<HomeViewState> {
        void render(HomeViewState state);
    }

    interface SchoolsPresenterActions {
        void goodRxloadSchools();
    }

}
