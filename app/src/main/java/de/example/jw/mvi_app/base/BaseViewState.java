package de.example.jw.mvi_app.base;

/**
 * Created by JW on 29.04.2017.
 */

public interface BaseViewState<S> {
    S reduce(S previous);
}
