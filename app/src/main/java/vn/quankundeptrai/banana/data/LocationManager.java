package vn.quankundeptrai.banana.data;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.interfaces.ILocationListener;

/**
 * Created by TQN on 2/7/18.
 */

public class LocationManager implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    // LogCat tag
    private static final String TAG = LocationManager.class.getSimpleName();
    private Location mLastLocation;
    private Context mContext;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    // register listener when response new location
    private ILocationListener mListenILocationResponse;

    private int mGMSResultCode = -1;

    public LocationManager(Context context) {
        this.mContext = context;

        if (isCheckedGooglePlayServicesAvailable()) {
            buildGoogleApiClient();
        }
    }

    /**
     * Creating location request object
     */
    private LocationRequest createLocationRequest() {

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(AppConstants.GPS_INTERVAL);
        locationRequest.setFastestInterval(AppConstants.GPS_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(AppConstants.GPS_DISPLACEMENT);

        return locationRequest;
    }

    /**
     * Starting the location updates
     */
    public void startLocationUpdates() {

        if (mGoogleApiClient.isConnected()) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, createLocationRequest(), LocationManager.this);
            }
        }
    }

    public void stopLocationUpdates() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
//            mGoogleApiClient.disconnect();
//            mGoogleApiClient=null;
        }
    }

    /**
     * Creating google api client object
     */
    private synchronized void buildGoogleApiClient() {
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this.mContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        connectGoogleAPIClient();
    }

    public void connectGoogleAPIClient() {
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();

    }

    public void setListenILocationResponse(ILocationListener mListenILocationResponse) {
        this.mListenILocationResponse = mListenILocationResponse;
    }

    /**
     * Method to verify google play services on the device
     */
    public boolean isCheckedGooglePlayServicesAvailable() {

        mGMSResultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this.mContext);

        if (mGMSResultCode != ConnectionResult.SUCCESS)
            return false;

        return true;

    }

    @Override
    public void onConnected(Bundle bundle) {

        if (mGoogleApiClient.isConnected()) {

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLastLocation = LocationServices.FusedLocationApi
                        .getLastLocation(mGoogleApiClient);
            }
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        connectGoogleAPIClient();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    /**
     * @return GMS error code
     * @id
     * @description Get GMS error code
     */
    public int getGMSErrorCode() {

        return mGMSResultCode;
    }

    public Location getLastKnowLocation() {

        if (mGoogleApiClient.isConnected()) {
            Location location = null;
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                location = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
            }

            return location;

        }

        return null;

    }

    @Override
    public void onLocationChanged(Location location) {
        CoreManager.getInstance().setCurrentLocation(location);
        stopLocationUpdates();
        if (mListenILocationResponse != null) {
            mListenILocationResponse.onChange(location);
        }
    }

    public void disconnectGoogleAPIClient() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}
