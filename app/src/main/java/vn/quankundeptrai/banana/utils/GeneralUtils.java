package vn.quankundeptrai.banana.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import java.io.File;

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
}
