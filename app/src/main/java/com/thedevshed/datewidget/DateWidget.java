package com.thedevshed.datewidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Implementation of App Widget functionality.
 */
public class DateWidget extends AppWidgetProvider {

    private static final String LOG_TAG = DateWidget.class.getSimpleName();


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        AlarmReceiver.scheduleAlarm(context);
        for (int i = 0; i < N; i++) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.date_widget);

            updateIcon(context, views);

            // Launch calendar app when the user taps on the header
            views.setOnClickPendingIntent(R.id.img_widget, CalendarIntent.createOpenCalendarPendingIntent(context));

            appWidgetManager.updateAppWidget(appWidgetIds[i], views);


        }

    }

    public void updateIcon(Context context, RemoteViews remoteViews) {
        int day = getDay(new Date());

        int iconId = context.getResources().getIdentifier("calendar_" + day, "drawable", context.getPackageName());
        setImage(remoteViews, R.id.img_widget, iconId);

    }

    public int getDay(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");
        String mDay = simpleDateFormat.format(date);
        return Integer.parseInt(mDay);
    }



    public static void updateWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, DateWidget.class);
        Intent intent = new Intent(context, DateWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetManager.getAppWidgetIds(componentName));
        context.sendBroadcast(intent);
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void setImage(RemoteViews rv, int viewId, int resId) {
        rv.setImageViewResource(viewId, resId);
    }


}

