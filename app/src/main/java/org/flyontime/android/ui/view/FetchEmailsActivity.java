package org.flyontime.android.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.flyontime.android.model.state.FetchEmailsViewState;
import org.flyontime.android.ui.contract.FetchEmailsViewPresenterContract;
import org.flyontime.android.ui.presenter.FetchEmailsPresenter;
import org.flyontime.jw.android.R;
import org.flyontime.jw.android.databinding.ActivityFetchEmailsBinding;
import org.jetbrains.annotations.NotNull;

public class FetchEmailsActivity extends AppCompatActivity implements FetchEmailsViewPresenterContract.FetchEmailsViewActions {

    private FetchEmailsViewPresenterContract.FetchEmailsPresenterActions presenter = new FetchEmailsPresenter(this);

    private ActivityFetchEmailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fetch_emails);
        presenter.searchMessages();
    }

    @Override
    public void render(@NotNull FetchEmailsViewState state) {

    }
}
