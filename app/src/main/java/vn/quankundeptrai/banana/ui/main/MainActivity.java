package vn.quankundeptrai.banana.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.customviews.general.NonSwipeViewPager;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.data.constants.ExtraKeys;
import vn.quankundeptrai.banana.dialogs.DialogUtils;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.interfaces.IJobListener;
import vn.quankundeptrai.banana.ui.adapter.PagerAdapter;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.menuactivities.favoritelocations.FavoriteLocationActivity;
import vn.quankundeptrai.banana.ui.menuactivities.feedback.FeedbackActivity;
import vn.quankundeptrai.banana.ui.menuactivities.help.HelpActivity;
import vn.quankundeptrai.banana.ui.menuactivities.notification.NotificationActivity;
import vn.quankundeptrai.banana.ui.menuactivities.rewards.RewardsActivity;
import vn.quankundeptrai.banana.utils.GeneralUtils;
import vn.quankundeptrai.banana.utils.InstantiateUtils;
import vn.quankundeptrai.banana.utils.MenuManager;
import vn.quankundeptrai.banana.utils.PermissionUtils;

public class MainActivity extends BaseActivity<MainPresenter> implements MainMvpView, IAdapterDataCallback, View.OnClickListener, OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {
    private MenuManager menuManager;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private boolean getCompleted = false, onActive = true;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initialView() {
        destroyHeader();
        ImageView menuBtn = mainView.findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(this);

        menuManager = new MenuManager(this, mainView, this);

        PermissionUtils.requestPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION, getString(R.string.no_location_permission), ExtraKeys.LOCATION_REQUEST, new IJobListener<Void>() {
            @Override
            public void onComplete(Void result) {
                handleLocation();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();

                if(mMap != null)
                    mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                ///checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            if(mMap != null)
                mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onItemClick(int position) {
        switch (position){
            case AppConstants.MENU_NOTI:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case AppConstants.MENU_FAV_LOCATION:
                startActivity(new Intent(this, FavoriteLocationActivity.class));
                break;
            case AppConstants.MENU_FEEDBACK:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case AppConstants.MENU_REWARDS:
                startActivity(new Intent(this, RewardsActivity.class));
                break;
            case AppConstants.MENU_HELP:
                startActivity(new Intent(this, HelpActivity.class));
                break;
            case AppConstants.MENU_FACEBOOK:
                openFacebook();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menuBtn :
                menuManager.openMenu();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Reload map
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
// Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng sydney = new LatLng(-33.852, 151.211);

        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        googleMap.addMarker(new MarkerOptions().
                position(mMap.getCameraPosition().target).title("Near to Sydney"));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.draggable(true);
                markerOptions.title("Add a hazard here.");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(markerOptions);
                ///mHazardsMarker = mMap.addMarker(markerOptions);
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng objLoc = marker.getPosition();
                return false;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                LatLng objLoc = marker.getPosition();
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }

    private void openFacebook(){
        Toast.makeText(this, "Opening Facebook...", Toast.LENGTH_SHORT).show();
    }

    public void handleLocation() {
        if (getCompleted || !onActive) {
            return;
        }

        if (GeneralUtils.checkGPS(this)) {
            getCompleted = true;
            CoreManager.getInstance().addOrUpdateLocationManager(this, null);
            getMyLocation();
        } else {
            DialogUtils.showMessage(this, getString(R.string.please_enable_gps_and_choose_high_accuracy_method_so_strawberry_can_estimate_your_location), getString(R.string.open_setting),
                    getString(R.string.cancel), new IJobListener<Void>() {
                        @Override
                        public void onComplete(Void result) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    }, null);
        }
    }

    private void getMyLocation() {
        getPresenter().waitLocation();
    }
}
