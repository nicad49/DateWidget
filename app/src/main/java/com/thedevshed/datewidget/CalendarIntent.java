package com.thedevshed.datewidget;

import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;

import java.util.Calendar;

/**
 * Created by doug on 2016-01-04.
 */
public class CalendarIntent {

    private static final String LOG_TAG = CalendarIntent.class.getSimpleName();

    private static final String KEY_DETAIL_VIEW = "DETAIL_VIEW";
    private static final String TIME = "time";

    static Intent openCalendarIntent() {
        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath(TIME);
        ContentUris.appendId(builder, Calendar.getInstance().getTimeInMillis());
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());

        intent.putExtra(KEY_DETAIL_VIEW, true);

        return intent;
    }

    static PendingIntent createOpenCalendarPendingIntent(Context context) {
        Intent intent = openCalendarIntent();
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


}
