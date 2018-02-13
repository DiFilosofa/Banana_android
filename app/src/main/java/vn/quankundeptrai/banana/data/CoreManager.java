package vn.quankundeptrai.banana.data;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import vn.quankundeptrai.banana.data.constants.ExtraKeys;
import vn.quankundeptrai.banana.data.constants.FormatConstants;
import vn.quankundeptrai.banana.data.models.other.User;
import vn.quankundeptrai.banana.interfaces.ILocationListener;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.utils.PreferenceUtils;

/**
 * Created by TQN on 1/19/2018.
 */

public class CoreManager {
    private static CoreManager _instance;
    private User user;
    private boolean notiOn = false;
    private String token = "";
    private Location currentLocation;
    private LocationManager locationManager;


    private BaseActivity currentActivity;
    private Gson gson, commonGson;

    private CoreManager() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        commonGson = new GsonBuilder()
                .setDateFormat(FormatConstants.SERVER_FORMAT)
                .excludeFieldsWithoutExposeAnnotation().create();
    }

    public static CoreManager getInstance() {
        if (_instance == null) {
            _instance = new CoreManager();
        }

        return _instance;
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public void setUser(User user) {
        this.user = user;
        this.token = user.getToken();
        PreferenceUtils.saveStringPref(ExtraKeys.TOKEN, gson.toJson(user.getToken()));
        PreferenceUtils.saveStringPref(ExtraKeys.USER, gson.toJson(user));
    }

    public User getUser() {
        if (user == null && isLogined(currentActivity)) {
            user = gson.fromJson(PreferenceUtils.getStringPref(ExtraKeys.USER, null), User.class);
        }
        return user;
    }

    public String getToken(){
        if(token.isEmpty() && isLogined(currentActivity)){
            token = getUser().getToken();
        }
        return token;
    }

    public boolean isLogined(Context context) {
        return PreferenceUtils.isExist(context, ExtraKeys.USER);
    }

    public void logout(){
        PreferenceUtils.remove(getCurrentActivity(), ExtraKeys.USER);
    }

    public Location getCurrentLocation() {
        return getCurrentLocation(currentActivity);
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        PreferenceUtils.saveFloatPref(ExtraKeys.LAT, (float) currentLocation.getLatitude());
        PreferenceUtils.saveFloatPref(ExtraKeys.LNG, (float) currentLocation.getLongitude());
    }

    private Location getCurrentLocation(Context context) {
        if (currentLocation == null && PreferenceUtils.isExist(context, ExtraKeys.LAT) && PreferenceUtils.isExist(context, ExtraKeys.LNG)) {
            currentLocation = new Location("");
            currentLocation.setLatitude(PreferenceUtils.getFloatPref(context, ExtraKeys.LAT, 0f));
            currentLocation.setLongitude(PreferenceUtils.getFloatPref(context, ExtraKeys.LNG, 0f));
        }

        return currentLocation;
    }

    public boolean isLocationReady() {
        return currentLocation != null;
    }

    public void addOrUpdateLocationManager(Context context, ILocationListener mListenILocationResponse) {
        if (locationManager == null) {
            locationManager = new LocationManager(context);
        }

        if (mListenILocationResponse != null) {
            locationManager.setListenILocationResponse(mListenILocationResponse);
        }
    }

    public void removeLocationManager() {
        if (locationManager != null) {
            locationManager.stopLocationUpdates();
            locationManager = null;
        }
    }

    public void setNotificationStatus(boolean on){
        PreferenceUtils.saveBoolPref(ExtraKeys.NOTI_STATUS, on);
    }

    public boolean isNotificationOn(){
        this.notiOn = PreferenceUtils.getBoolPref(ExtraKeys.NOTI_STATUS, false);
        return notiOn;
    }

}
