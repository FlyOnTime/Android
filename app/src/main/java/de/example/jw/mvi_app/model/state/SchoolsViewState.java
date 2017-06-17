package de.example.jw.mvi_app.model.state;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;

import de.example.jw.mvi_app.base.BaseViewState;
import de.example.jw.mvi_app.model.data.SchoolsModel;

/**
 * Created by JW on 23.04.2017.
 */

public class SchoolsViewState implements BaseViewState<SchoolsViewState> {

    // TODO: Has this state model to be changed for our purposes?
    // => Check with UX/UI Designers

    private boolean loading;
    private Throwable error;
    private List<SchoolsModel> schools;

    private SchoolsViewState(boolean loading, Throwable error, List<SchoolsModel> schools) {
        this.loading = loading;
        this.error = error;
        this.schools = schools;
    }

    public static SchoolsViewState LoadingState() {
        return new SchoolsViewState(true, null, null);
    }

    public static SchoolsViewState ErrorState(Throwable error) {
        return new SchoolsViewState(false, error, null);
    }

    public static SchoolsViewState DataLoadedState(List<SchoolsModel> schools) {
        return new SchoolsViewState(false, null, schools);
    }

    public boolean isLoading() {
        return loading;
    }

    public Throwable getError() {
        return error;
    }

    public List<SchoolsModel> getSchools() {
        return schools;
    }

    @Override
    public SchoolsViewState reduce(SchoolsViewState previous) {
        //TODO
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        SchoolsViewState state = (SchoolsViewState) obj;

        boolean areListsEqual = !(this.schools != null && state.getSchools() != null) || CollectionUtils.isEqualCollection(this.schools, state.getSchools());
        boolean isLoadingEqual = state.isLoading() == this.loading;
        boolean areErrorsEqual = Objects.equals(state.getError(), this.error);

        return isLoadingEqual && areErrorsEqual && areListsEqual;
    }
}