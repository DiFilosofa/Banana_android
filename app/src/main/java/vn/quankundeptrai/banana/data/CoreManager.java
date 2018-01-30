package vn.quankundeptrai.banana.data;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import vn.quankundeptrai.banana.data.constants.ExtraKeys;
import vn.quankundeptrai.banana.data.constants.FormatConstants;
import vn.quankundeptrai.banana.data.models.other.User;
import vn.quankundeptrai.banana.utils.PreferenceUtils;

/**
 * Created by TQN on 1/19/2018.
 */

public class CoreManager {
    private static CoreManager _instance;
    private User user;
    private String token = "";

    private Activity currentActivity;
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

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
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
}
