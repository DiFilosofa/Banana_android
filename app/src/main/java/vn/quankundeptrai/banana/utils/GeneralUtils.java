package vn.quankundeptrai.banana.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleLatLng;
import vn.quankundeptrai.banana.interfaces.IDialogCallback;

import static android.provider.Settings.Secure.LOCATION_MODE_HIGH_ACCURACY;

/**
 * Created by TQN on 1/19/2018.
 */

public class GeneralUtils {
    public static boolean isNetworkOnline(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public static void emptyFolder(String directory) {
        File root = new File(directory);
        if (root.exists()) {
            File[] directoryContents = root.listFiles();
            for (File file : directoryContents)
                file.delete();
        }
    }

    public static int getColorFromResource(Context context, int resId) {
        return ContextCompat.getColor(context, resId);
    }

    public static boolean checkGPS(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            int mode;
            try {
                mode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                return checkGPSProvider(context);
            }

            return mode == LOCATION_MODE_HIGH_ACCURACY;
        }

        return checkGPSProvider(context);
    }

    private static boolean checkGPSProvider(Context context) {
        return ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE)).isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static float getDistance(GoogleLatLng start, GoogleLatLng end) {
        Location startLoc = new Location("");
        Location endLoc = new Location("");
        startLoc.setLatitude(start.getLat());
        startLoc.setLongitude(start.getLng());
        endLoc.setLatitude(end.getLat());
        endLoc.setLongitude(end.getLng());
        return startLoc.distanceTo(endLoc);
    }
}
