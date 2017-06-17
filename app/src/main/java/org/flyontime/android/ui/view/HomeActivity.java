package org.flyontime.android.ui.view;

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

import org.flyontime.android.model.data.ItemModel;
import org.flyontime.android.model.state.HomeViewState;
import org.flyontime.android.ui.adapter.HomeAdapter;
import org.flyontime.android.ui.contract.HomeViewPresenterContract;
import org.flyontime.android.ui.presenter.HomePresenter;
import org.flyontime.jw.android.R;
import org.flyontime.jw.android.databinding.ActivityMainBinding;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeViewPresenterContract.SchoolsViewActions {

    private HomeViewPresenterContract.SchoolsPresenterActions presenter = new HomePresenter(this);

    private ActivityMainBinding binding;

    //private BeaconManager beaconManager = new BeaconManager(getApplicationContext());

    @Override
    protected void onStart() {
        super.onStart();
        //beaconManager.connect(() -> beaconManager.startNearableDiscovery());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //beaconManager.disconnect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //beaconManager.stopNearableDiscovery();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupRecyclerView();
        setupSwiperefresh();

        // Should be invoked in #onCreate.

        /*beaconManager.setNearableListener(nearables -> {
            Gson gson = new Gson();
            for(Nearable nearable : nearables) {
                Log.d("HOMEACTIVITY", gson.toJson(nearable));
            }
        });*/

        presenter.goodRxloadSchools();

    }

    void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(mLayoutManager);

        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(new HomeAdapter(this.getLayoutInflater()));
    }

    void setupSwiperefresh() {
        binding.swiperefresh.setOnRefreshListener(() -> presenter.goodRxloadSchools());
    }

    @Override
    public void render(HomeViewState state) {
        if (state.getError() != null) {
            showError(state.getError().getMessage());
        }
        if (state.isLoading()) {
            showLoader();
            clearAdapter();
        } else if (!state.isLoading()) {
            hideLoader();
        }
        if (state.getItems() != null) {
            showSchools(state.getItems());
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
                ((HomeAdapter) binding.recyclerView.getAdapter()).getFilter().filter(text);
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
        ((HomeAdapter) binding.recyclerView.getAdapter()).clear();
    }

    void showSchools(List<ItemModel> items) {
        for (ItemModel item : items) {
            ((HomeAdapter) binding.recyclerView.getAdapter()).add(item);
        }
    }
}