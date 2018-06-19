package vn.quankundeptrai.banana.ui.main.map;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.Handler;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.ui.base.BaseFragment;
import vn.quankundeptrai.banana.ui.base.BasePresenter;
import vn.quankundeptrai.banana.utils.GeneralUtils;

/**
 * Created by TQN on 1/20/2018.
 */

public class MapUtils extends BaseFragment implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter  {
    private GoogleMap mMap;
    private double lat, lng;
    private Bitmap bitmap;
    private GoogleMap.OnCameraMoveListener mapMoveListener;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_google_map;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    @Override
    protected void initialView() {

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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(this);

        if (mapMoveListener != null) {
            mMap.setOnCameraMoveListener(mapMoveListener);
        }

        if (lat != 0d || lng != 0d) {
            jump(lat, lng);
        } else if (GeneralUtils.checkGPS(getCurrentActivity())) {
            jump(new Handler());
        }
    }

    private void jump(final Handler handler) {
        if (CoreManager.getInstance().isLocationReady()) {
            Location location = CoreManager.getInstance().getCurrentLocation();
            jump(location.getLatitude(), location.getLongitude());
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    jump(handler);
                }
            }, 500);
        }
    }

    public void jump(double lat, double lng) {
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                .target(new LatLng(lat, lng)).zoom(15f).build()));
    }
    public void setupMap() {
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.myMap)).getMapAsync(this);
    }

    public void updateLocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
        setupMap();
    }
}
