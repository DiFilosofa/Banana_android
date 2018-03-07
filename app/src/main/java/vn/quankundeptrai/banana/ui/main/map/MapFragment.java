package vn.quankundeptrai.banana.ui.main.map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.Pair;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.data.constants.ExtraKeys;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleDirectionRoutes;
import vn.quankundeptrai.banana.dialogs.DialogUtils;
import vn.quankundeptrai.banana.interfaces.IAdapterDataCallback;
import vn.quankundeptrai.banana.interfaces.IJobListener;
import vn.quankundeptrai.banana.ui.base.BaseFragment;
import vn.quankundeptrai.banana.ui.createevent.CreateNewEventActivity;
import vn.quankundeptrai.banana.ui.menu.favoritelocations.FavoriteLocationActivity;
import vn.quankundeptrai.banana.ui.menu.feedback.FeedbackActivity;
import vn.quankundeptrai.banana.ui.menu.help.HelpActivity;
import vn.quankundeptrai.banana.ui.menu.notification.NotificationActivity;
import vn.quankundeptrai.banana.ui.menu.rewards.RewardsActivity;
import vn.quankundeptrai.banana.utils.GeneralUtils;
import vn.quankundeptrai.banana.utils.MenuManager;
import vn.quankundeptrai.banana.utils.PermissionUtils;

/**
 * Created by TQN on 2/13/18.
 */

public class MapFragment extends BaseFragment<MapPresenter> implements MapMvpView, View.OnClickListener, IAdapterDataCallback, OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private MenuManager menuManager;
    private boolean getCompleted = false, onActive = true;
    private ArrayList<Event> events = null;
    private View buttonPanel, dialog, densityBoard;
    private TextView dialogText;
    private int showDialogState = 0; //0 -> no show, 1 -> picking begin point, 2 -> picking end point, 3 -> showing road
    private Marker beginMarker = null, endMarker = null;
    private Polyline tempEvent = null;
    private GoogleDirectionRoutes newEventRoute = null;

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
        getCurrentActivity().showLoading();
        mainView.findViewById(R.id.postEventBtn).setOnClickListener(this);
        mainView.findViewById(R.id.layerBtn).setOnClickListener(this);
        mainView.findViewById(R.id.currentLocationBtn).setOnClickListener(this);
        mainView.findViewById(R.id.select).setOnClickListener(this);
        mainView.findViewById(R.id.unselect).setOnClickListener(this);
        buttonPanel = mainView.findViewById(R.id.buttonPanel);
        dialog = mainView.findViewById(R.id.dialog);
        dialogText = mainView.findViewById(R.id.dialogText);
        densityBoard = mainView.findViewById(R.id.densityBoard);

        ImageView menuBtn = mainView.findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(this);

        menuManager = new MenuManager(getCurrentActivity(), mainView, this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapFragment);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                handleLocation();
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
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menuBtn:
                menuManager.openMenu();
                break;
            case R.id.postEventBtn:
                showDialog(true);
                break;
            case R.id.currentLocationBtn:
                jumpToCurrentLocation();
                break;
            case R.id.layerBtn:
                break;
            case R.id.select:
                switch (showDialogState) {
                    case 1: //pick start point done
                        if (beginMarker != null) {
                            showDialogState = 2;
                            setDialogText();
                        } else {
                            Toast.makeText(getCurrentActivity(), getResString(R.string.please_select_start_point), Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2: //pick end point done
                        if (endMarker != null) {
                            getCurrentActivity().showLoading();
                            getPresenter().getRoute(
                                    new Pair<LatLng, LatLng>(
                                            beginMarker.getPosition(),
                                            endMarker.getPosition()),
                                    ContextCompat.getColor(getCurrentActivity(), R.color.colorPrimaryDark));
                        } else {
                            Toast.makeText(getCurrentActivity(), getResString(R.string.please_select_end_point), Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        getCurrentActivity().startBaseActivityWithExtra(getCurrentActivity(), CreateNewEventActivity.class, ExtraKeys.NEW_EVENT, newEventRoute);
                        break;
                }
                break;
            case R.id.unselect:
                switch (showDialogState) {
                    case 1: //pick start point done
                        if (beginMarker != null) {
                            clearMarker(beginMarker);
                        }
                        showDialog(false);
                        break;
                    case 2: //pick end point done
                        if (endMarker != null) {
                            clearMarker(endMarker);
                        }
                        showDialogState = 1;
                        setDialogText();
                        break;
                    case 3:
                        tempEvent.remove();
                        newEventRoute = null;
                        showDialogState = 2;
                        setDialogText();
                        break;
                }
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
        mMap.setOnMapClickListener(this);
        jumpToCurrentLocation();
        getCurrentActivity().hideLoading();
    }


    private void drawEvents(Pair<GoogleDirectionRoutes, Integer> newEvent) {
        getCurrentActivity().hideLoading();
        if (showDialogState > 0) {
            tempEvent = mMap.addPolyline(new PolylineOptions()
                    .addAll(newEvent.first.getRoutes().getPolylineList())
                    .color(newEvent.second)
                    .width(11)
                    .visible(true));
            return;
        }
        mMap.addPolyline(new PolylineOptions()
                .addAll(newEvent.first.getRoutes().getPolylineList())
                .color(newEvent.second)
                .width(11)
                .visible(true));
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
        Toast.makeText(getCurrentActivity(), getResString(R.string.google_map_connect_fail), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onHasDirection(GoogleDirectionRoutes route, Integer color) {
        drawEvents(new Pair<GoogleDirectionRoutes, Integer>(route, color));
        if (beginMarker != null && endMarker != null) {
            showDialogState = 3;
            dialogText.setText(getResString(R.string.display_road));
            newEventRoute = route;
        }
    }

    @Override
    public void onNoDirection() {
        getCurrentActivity().hideLoading();
    }

    public void updateEvent(ArrayList<Event> list) {
        if (mMap !=null) {
            mMap.clear();
        }
        showDialog(false);

        getCurrentActivity().showLoading();
        events = list;
        if (events != null && !events.isEmpty()) {
            for (Event event : events) {
                int color;
                switch (event.getDensity()) {
                    case NORMAL:
                        color = ContextCompat.getColor(getContext(), R.color.density_1);
                        break;
                    case QUITE_CROWDED:
                        color = ContextCompat.getColor(getContext(), R.color.density_2);
                        break;
                    case CROWDED:
                        color = ContextCompat.getColor(getContext(), R.color.density_3);
                        break;
                    case VERY_CROWDED:
                        color = ContextCompat.getColor(getContext(), R.color.density_4);
                        break;
                    default:
                        color = ContextCompat.getColor(getContext(), R.color.density_0);
                        break;
                }
                getPresenter().getRoute(new Pair<LatLng, LatLng>(
                        new LatLng(event.getLat(), event.getLng()),
                        new LatLng(event.getEndLat(), event.getEndLng())
                ), color);
            }
        }
    }

    private void jumpToCurrentLocation() {
        Location myLocation = CoreManager.getInstance().getCurrentLocation();
        LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        if (PermissionUtils.checkPermission(getCurrentActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            mMap.setMyLocationEnabled(true);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 16.0f));
    }

    private void showDialog(boolean show) {
        if (show) {
            showDialogState = 1;
            buttonPanel.setVisibility(View.GONE);
            dialog.setVisibility(View.VISIBLE);
            densityBoard.setVisibility(View.GONE);
        } else {
            showDialogState = 0;
            dialog.setVisibility(View.GONE);
            buttonPanel.setVisibility(View.VISIBLE);
            densityBoard.setVisibility(View.VISIBLE);
            if (endMarker != null) {
                endMarker.remove();
                beginMarker.remove();
                newEventRoute = null;
                tempEvent.remove();
            }
        }
    }

    @Override
    public void onMapClick(LatLng point) {
        if (showDialogState == 1) {
            if (beginMarker != null) {
                clearMarker(beginMarker);
            }
            beginMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(point.latitude, point.longitude)));
        } else if (showDialogState == 2) {
            if (endMarker != null) {
                clearMarker(endMarker);
            }
            endMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(point.latitude, point.longitude)));
        }
    }

    private void clearMarker(Marker marker) {
        marker.remove();
        marker = null;
    }

    private void setDialogText() {
        dialogText.setText(getResString(showDialogState == 1 ? R.string.pick_start_point : R.string.pick_end_point));
    }
}
