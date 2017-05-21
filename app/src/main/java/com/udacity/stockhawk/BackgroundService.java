package com.udacity.stockhawk;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class BackgroundService extends Service {

    public static int SERVICE_ID = 123;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("Service", "MyService Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Service", "MyService onStartCommand");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, StockHawkProvider.class));
        if (appWidgetIds.length != 0) {
            StockHawkProvider.updateAppWidget(this, appWidgetManager, appWidgetIds[0]);
        }
        return Service.START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i("Service", "MyService onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onDestroy() {
        Log.i("Service", "MyService Destroyed");
        super.onDestroy();
    }
}