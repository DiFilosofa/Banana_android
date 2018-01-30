package vn.quankundeptrai.banana.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;

import java.io.File;

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
}
