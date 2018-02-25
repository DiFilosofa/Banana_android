package vn.quankundeptrai.banana.ui.main.map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
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

import java.util.ArrayList;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.data.constants.ExtraKeys;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.dialogs.DialogUtils;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.interfaces.IJobListener;
import vn.quankundeptrai.banana.ui.base.BaseFragment;
import vn.quankundeptrai.banana.ui.menuactivities.favoritelocations.FavoriteLocationActivity;
import vn.quankundeptrai.banana.ui.menuactivities.feedback.FeedbackActivity;
import vn.quankundeptrai.banana.ui.menuactivities.help.HelpActivity;
import vn.quankundeptrai.banana.ui.menuactivities.notification.NotificationActivity;
import vn.quankundeptrai.banana.ui.menuactivities.rewards.RewardsActivity;
import vn.quankundeptrai.banana.utils.GeneralUtils;
import vn.quankundeptrai.banana.utils.MenuManager;
import vn.quankundeptrai.banana.utils.PermissionUtils;

/**
 * Created by TQN on 2/13/18.
 */

public class MapFragment extends BaseFragment<MapPresenter> implements MapMvpView, View.OnClickListener, IAdapterDataCallback, OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private MenuManager menuManager;
    private boolean getCompleted = false, onActive = true;
    private GoogleMap.OnCameraMoveListener mapMoveListener;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_map;
    }

    @Override
    protected MapPresenter onCreatePresenter() {
        return new MapPresenter();
    }

    @Override
    protected void initialView() {

        ImageView menuBtn = mainView.findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(this);

        menuManager = new MenuManager(getCurrentActivity(), mainView, this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapFragment);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();

                if (mMap != null) {
                    mMap.setMyLocationEnabled(true);
                }
                mapFragment.getMapAsync(this);

            } else {
                PermissionUtils.requestPermissions(getCurrentActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        getString(R.string.no_location_permission), ExtraKeys.LOCATION_REQUEST,
                        new IJobListener<Void>() {
                            @Override
                            public void onComplete(Void result) {
                                handleLocation();
                            }
                        }
                );
            }
        } else {
            buildGoogleApiClient();
            if (mMap != null)
                mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menuBtn:
                menuManager.openMenu();
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case AppConstants.MENU_NOTI:
                startActivity(new Intent(getCurrentActivity(), NotificationActivity.class));
                break;
            case AppConstants.MENU_FAV_LOCATION:
                startActivity(new Intent(getCurrentActivity(), FavoriteLocationActivity.class));
                break;
            case AppConstants.MENU_FEEDBACK:
                startActivity(new Intent(getCurrentActivity(), FeedbackActivity.class));
                break;
            case AppConstants.MENU_REWARDS:
                startActivity(new Intent(getCurrentActivity(), RewardsActivity.class));
                break;
            case AppConstants.MENU_HELP:
                startActivity(new Intent(getCurrentActivity(), HelpActivity.class));
                break;
            case AppConstants.MENU_FACEBOOK:
                openFacebook();
                break;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setInfoWindowAdapter(this);
//        mMap.setOnInfoWindowClickListener(this);
//        if (mapMoveListener != null) {
//            mMap.setOnCameraMoveListener(mapMoveListener);
//        }

        Location myLocation = CoreManager.getInstance().getCurrentLocation();

        LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

        googleMap.addMarker(new MarkerOptions().position(myLatLng).title("I'm here"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));

        googleMap.addMarker(new MarkerOptions().position(mMap.getCameraPosition().target).title("Near me"));

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

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), this)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void getMyLocation() {
        getPresenter().waitLocation();
    }

    public void handleLocation() {
        if (getCompleted || !onActive) {
            return;
        }

        if (GeneralUtils.checkGPS(getContext())) {
            getCompleted = true;
            CoreManager.getInstance().addOrUpdateLocationManager(getContext(), null);
            getMyLocation();
        } else {
            DialogUtils.showMessage(getContext(), getString(R.string.please_enable_gps_and_choose_high_accuracy_method_so_strawberry_can_estimate_your_location), getString(R.string.open_setting),
                    getString(R.string.cancel), new IJobListener<Void>() {
                        @Override
                        public void onComplete(Void result) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    }, null);
        }
    }

    private void openFacebook() {
        Toast.makeText(getCurrentActivity(), "Opening Facebook...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    public void updateEvent(ArrayList<Event> list){

    }
}
