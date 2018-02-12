package vn.quankundeptrai.banana.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import vn.quankundeptrai.banana.interfaces.IJobListener;

/**
 * Created by TQN on 1/19/2018.
 */

public class DialogUtils {
    public static void showMessage(Context context, String message, String positiveButton, String negativeButton, final IJobListener<Void> positiveCallback, final IJobListener<Void> negativeCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setCancelable(false);

        if (message != null) {
            builder.setMessage(message);
        }

        if (positiveButton != null) {
            builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (positiveCallback != null) {
                        positiveCallback.onComplete(null);
                    }
                }
            });
        }

        if (negativeButton != null) {
            builder.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (negativeCallback != null) {
                        negativeCallback.onComplete(null);
                    }
                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
