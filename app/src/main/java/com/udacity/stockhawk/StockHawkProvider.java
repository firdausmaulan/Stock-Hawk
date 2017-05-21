package com.udacity.stockhawk;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.udacity.stockhawk.ui.MainActivity;

import static com.udacity.stockhawk.ui.MainActivity.indexWidget;
import static com.udacity.stockhawk.ui.MainActivity.listCompany;
import static com.udacity.stockhawk.ui.MainActivity.listUpDown;

/**
 * Implementation of App Widget functionality.
 */
public class StockHawkProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.stock_widget);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.image_widget, pendingIntent);
        views.setOnClickPendingIntent(R.id.text_widget, pendingIntent);

        try {
            String description = context.getResources().getString(R.string.app_name);
            if (listCompany != null) {
                if ((listCompany.size() - 1) == indexWidget) {
                    indexWidget = 0;
                }
                if (listCompany.size() > 0) {
                    Log.d("listCompany", listCompany.size() + "");
                    description = listCompany.get(indexWidget);
                }
            }
            if (listUpDown != null) {
                if (listUpDown.size() > 0) {
                    Log.d("listUpDown", listUpDown.size() + "");
                    description = description + " | " + listUpDown.get(indexWidget);
                }
            }
            views.setTextViewText(R.id.text_widget, description);
            indexWidget++;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

