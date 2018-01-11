package com.example.mahmoud.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mahmoud.bakingapp.Model.BakingDetail;
import com.example.mahmoud.bakingapp.Model.BakingIngrediant;
import com.example.mahmoud.bakingapp.Model.BakingSteps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
    
import javax.security.auth.login.LoginException;


public class RecipeWidgetServices extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d("a7a","onGetViewFactory");
        return new IngredientRemoteViewFactor(this.getApplicationContext(),intent);

    }
}

class IngredientRemoteViewFactor implements RemoteViewsService.RemoteViewsFactory{

    private static final String TAG = IngredientRemoteViewFactor.class.getSimpleName() +"WIDGETLOG";
    private Context mContext ;
    private int mAppWidgetId;
    private List<BakingDetail> mRecipes;

    IngredientRemoteViewFactor(Context context,Intent intent){
        this.mContext = context;
        Log.d(TAG, "IngredientRemoteViewFactor: ");
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        mRecipes = new ArrayList<>();
        final long token = Binder.clearCallingIdentity();

        try{
            final String recipeUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
            RequestQueue mQueue = Volley.newRequestQueue(mContext);

            Log.d(TAG, "onDataSetChanged: ");

            final JsonArrayRequest mRequest = new JsonArrayRequest(Request.Method.GET,recipeUrl,(String) null,new Response.Listener<JSONArray>(){
                @Override
                public void onResponse(JSONArray response){

                    try {
                        Log.d(TAG, "last recipe id: "+getLastRecipeId());
                        mRecipes.addAll(formatResponse(response,getLastRecipeId()));
                        AppWidgetManager.getInstance(mContext).notifyAppWidgetViewDataChanged(mAppWidgetId,R.id.lv_ingredient);
                        Log.d(TAG, "onResponse: "+mRecipes.toString());

                    } catch (JSONException e) {
                        Log.e(TAG, "onResponse: ",e.getCause() );
                        e.printStackTrace();
                    }

                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.e(TAG, "onErrorResponse: ",error.getCause() );
                }
            });
            mQueue.add(mRequest);

        }finally {
            Binder.restoreCallingIdentity(token);
        }
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }
    public  int getLastRecipeId(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getInt("currentPos",-1);
    }
    // convert JsonArray to BakingDetail
    private List<BakingDetail>formatResponse(JSONArray response,int choosenId) throws JSONException {
        List<BakingIngrediant> ingrediants;
        List<BakingSteps> steps;
        List<BakingDetail> result = new ArrayList<>();

        JSONObject recipeObject , ingredientObject , stepObject;
        JSONArray ingredientArray,stepArray;
        for(int i=0;i<response.length();i++){
            if(i== choosenId){
                steps = new ArrayList<>();
                ingrediants = new ArrayList<>();

                recipeObject = response.getJSONObject(i);
                ingredientArray = recipeObject.getJSONArray("ingredients");
                stepArray = recipeObject.getJSONArray("steps");

                for (int k=0;k<ingredientArray.length();k++){
                    ingredientObject = ingredientArray.getJSONObject(k);
                    ingrediants.add(new BakingIngrediant(
                            ingredientObject.getString("measure"),
                            ingredientObject.getString("ingredient"),
                            ingredientObject.getInt("quantity")
                    ));
                    Log.d("a7aaaaaa", ingredientObject.getString("quantity"));
                }

                for(int k=0;k<stepArray.length();k++){
                    stepObject = stepArray.getJSONObject(k);
                    Log.d(TAG, "formatResponse: "+stepObject.get("videoURL"));
                    steps.add(new BakingSteps(stepObject.getInt("id"),
                            stepObject.getString("shortDescription"),
                            stepObject.getString("description"),
                            stepObject.getString("videoURL"),
                            ""

                    ));
                }
                result.add(new BakingDetail(
                        recipeObject.getString("name"),
                        recipeObject.getInt("servings"),
                        ingrediants,
                        steps
                ));
            }
        }
        // 5aly balek mn hena check it
        return  result;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: "+mRecipes.size());
        return mRecipes.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if(getCount() == 0){
            return getLoadingView();
        }
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),R.layout.baking_widget_provider);
        remoteViews.setTextViewText(R.id.tv_widget_recipe_name,mRecipes.get(position).getRecipeCard());


        List<BakingIngrediant> ingredients = mRecipes.get(position).getIngrediantlist();
        String stringIngredient = "";

        for(BakingIngrediant bakingIngrediant : ingredients){
            stringIngredient += mContext.getString(R.string.ingredientSingle,
                    bakingIngrediant.getIngrediantsQuantity()+"",bakingIngrediant.getIngrediantsMeasure(),
                    bakingIngrediant.getIngrediantsIngredient());
            Log.d(TAG, "getViewAt: "+stringIngredient);

        }
        remoteViews.setTextViewText(R.id.tv_widget_recipe_ingredient,stringIngredient);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return new RemoteViews(mContext.getPackageName(),R.layout.baking_widget_loading);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
