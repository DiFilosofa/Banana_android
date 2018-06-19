package vn.quankundeptrai.banana.utils;

import android.content.Context;

import java.util.Date;

import vn.quankundeptrai.banana.R;

/**
 * Created by TQN on 2/14/18.
 */

public class DateTimeUtils {
    public static String getTimeAgo(Context context, Date date) {
        long duration = (System.currentTimeMillis() - date.getTime()) / 60000;

        if (duration < 60) {
            return String.format(context.getString(R.string.posted_min_ago), duration);
        }

        if (duration < 120) {
            return context.getString(R.string.posted_1_hour_ago);
        }

        if (duration < 60 * 24) {
            return String.format(context.getString(R.string.posted_hour_ago), duration / 60);
        }

        if (duration < 2 * 60 * 24) {
            return context.getString(R.string.yesterday);
        }

        return String.format(context.getString(R.string.posted_day_ago), duration / (60 * 24));
    }
}
