package de.example.jw.mvi_app.ui.contract;

import de.example.jw.mvi_app.base.BaseViewPresenterContract;
import de.example.jw.mvi_app.model.state.SchoolsViewState;

/**
 * Created by JW on 23.04.2017.
 */

public interface HomeViewPresenterContract {

    interface SchoolsViewActions extends BaseViewPresenterContract.BaseViewActions<SchoolsViewState> {
        void render(SchoolsViewState state);
    }

    interface SchoolsPresenterActions {
        void normalRxLoadSchools();

        void goodRxloadSchools();
    }

}
