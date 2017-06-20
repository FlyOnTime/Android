package org.flyontime.android.ui.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.estimote.coresdk.recognition.packets.Nearable;
import com.estimote.coresdk.service.BeaconManager;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.flyontime.android.model.data.FlyOnTime.AppBarModel;
import org.flyontime.android.model.data.FlyOnTime.DashboardModelInterface;
import org.flyontime.android.model.state.HomeViewState;
import org.flyontime.android.ui.adapter.DashboardAdapter;
import org.flyontime.android.ui.contract.HomeViewPresenterContract;
import org.flyontime.android.ui.presenter.HomePresenter;
import org.flyontime.jw.android.R;
import org.flyontime.jw.android.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import br.com.goncalves.pugnotification.notification.PugNotification;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity implements HomeViewPresenterContract.HomeViewActions {

    boolean notificationShown = false;
    int inc = 0;
    private HomeViewPresenterContract.HomePresenterActions presenter = new HomePresenter(this);
    private ActivityMainBinding binding;
    private ArrayList<String> knownBeacons = new ArrayList<>();
    private BeaconManager beaconManager;

    @Override
    protected void onStart() {
        super.onStart();
        //beaconManager = new BeaconManager(this);
        beaconManager.connect(() -> beaconManager.startNearableDiscovery());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.disconnect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        beaconManager.stopNearableDiscovery();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        beaconManager = new BeaconManager(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupRecyclerView();
        setupSwiperefresh();

        binding.profileImage.setOnClickListener(v -> {
            Intent intent = new Intent(this, FetchEmailsActivity.class);
            startActivity(intent);
        });

        presenter.goodRxloadSchools();

    }

    void getPermissions() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        beaconManager.setNearableListener(nearables -> {
                            for (Nearable nearable : nearables) {
                                if (knownBeacons.contains(nearable.identifier)) {
                                    PugNotification.with(HomeActivity.this)
                                            .load()
                                            .title("Thanks for being on time!")
                                            .message("Get your free coffee now at Starbucks :)")
                                            .smallIcon(R.mipmap.ic_launcher)
                                            .simple()
                                            .build();
                                    notificationShown = true;
                                }
                                Log.d("ACTIVITY", nearable.identifier);
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
    }

    void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(mLayoutManager);

        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(new DashboardAdapter(this.getLayoutInflater()));
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
                ((DashboardAdapter) binding.recyclerView.getAdapter()).getFilter().filter(text);
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
        ((DashboardAdapter) binding.recyclerView.getAdapter()).clear();
    }

    void showSchools(List<DashboardModelInterface> items) {
        for (DashboardModelInterface item : items) {
            ((DashboardAdapter) binding.recyclerView.getAdapter()).add(item);
        }
        binding.setAppBarModel(new AppBarModel("Finnair Flight 913", "LSDA8H Helsinki - Berlin", "25 Jul. 11:30"));
    }
}
