package com.example.mahmoud.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.mahmoud.bakingapp.Model.BakingIngrediant;

import static android.content.ContentValues.TAG;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {


    private static final String TAG_MSG = BakingWidgetProvider.class.getSimpleName();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Log.d(TAG_MSG,"updateAppWidget: ");

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget_ingredients);
        // create an intent to lunch the mainScreen
        Intent intent = new Intent(context,RecipeWidgetServices.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        views.setRemoteAdapter(R.id.lv_ingredient,intent);

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.lv_ingredient);
        //PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

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

