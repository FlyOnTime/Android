package org.flyontime.android.base;

/**
 * Created by JW on 25.04.2017.
 */

public interface BaseViewPresenterContract {

    interface BaseViewActions<T> {
        void render(T state);
    }

}