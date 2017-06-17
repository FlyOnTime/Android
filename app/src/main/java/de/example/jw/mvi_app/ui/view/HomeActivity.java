package de.example.jw.mvi_app.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.List;

import de.example.jw.mvi_app.R;
import de.example.jw.mvi_app.databinding.ActivityMainBinding;
import de.example.jw.mvi_app.model.data.SchoolsModel;
import de.example.jw.mvi_app.model.state.SchoolsViewState;
import de.example.jw.mvi_app.ui.adapter.SchoolsAdapter;
import de.example.jw.mvi_app.ui.contract.HomeViewPresenterContract;
import de.example.jw.mvi_app.ui.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeViewPresenterContract.SchoolsViewActions {

    private HomeViewPresenterContract.SchoolsPresenterActions presenter = new HomePresenter(this);

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupRecyclerView();
        setupSwiperefresh();

        presenter.goodRxloadSchools();

    }

    void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(new SchoolsAdapter(this.getLayoutInflater()));
    }

    void setupSwiperefresh() {
        binding.swiperefresh.setOnRefreshListener(() -> presenter.goodRxloadSchools());
    }

    @Override
    public void render(SchoolsViewState state) {
        if (state.getError() != null) {
            showError(state.getError().getMessage());
        }
        if (state.isLoading()) {
            showLoader();
            clearAdapter();
        } else if (!state.isLoading()) {
            hideLoader();
        }
        if (state.getSchools() != null) {
            showSchools(state.getSchools());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_schools_activity, menu);

        SearchView searchView = (SearchView) menu.getItem(0).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                ((SchoolsAdapter) binding.recyclerView.getAdapter()).getFilter().filter(text);
                return true;
            }
        });


        return true;
    }

    void hideLoader() {
        binding.swiperefresh.setRefreshing(false);
    }

    void showLoader() {
        binding.swiperefresh.setRefreshing(true);
    }

    void showError(String error) {
        Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_SHORT).show();
    }

    void clearAdapter() {
        ((SchoolsAdapter) binding.recyclerView.getAdapter()).clear();
    }

    void showSchools(List<SchoolsModel> schools) {
        for (SchoolsModel school : schools) {
            ((SchoolsAdapter) binding.recyclerView.getAdapter()).add(school);
        }
    }
}
