package vn.quankundeptrai.banana.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.dialogs.DialogUtils;
import vn.quankundeptrai.banana.interfaces.IJobListener;
import vn.quankundeptrai.banana.interfaces.IPermissionCallback;
import vn.quankundeptrai.banana.ui.base.BaseActivity;

/**
 * Created by TQN on 2/9/18.
 */

public class PermissionUtils {
    public static void requestPermissions(final BaseActivity activity, final String permission, final String neverMsg, final int mainRequestCode, final IJobListener<Void> listener) {
        if (checkPermission(activity, permission)) {
            listener.onComplete(null);
        } else {
            activity.setPermissionCallback(new IPermissionCallback() {
                @Override
                public void onRequest(int requestCode, boolean isSuccess) {
                    if (requestCode == mainRequestCode) {
                        if (isSuccess) {
                            listener.onComplete(null);
                        } else if (Build.VERSION.SDK_INT >= 23 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                            DialogUtils.showMessage(activity, neverMsg, activity.getString(R.string.open_setting), activity.getString(R.string.cancel), new IJobListener<Void>() {
                                @Override
                                public void onComplete(Void result) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                                    intent.setData(uri);
                                    activity.startActivity(intent);
                                }
                            }, null);
                        }
                    }
                }
            });
            ActivityCompat.requestPermissions(activity, new String[]{permission}, mainRequestCode);
        }
    }

    public static boolean checkPermission(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == PackageManager.PERMISSION_GRANTED;
    }
}
