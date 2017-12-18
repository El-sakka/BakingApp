package com.example.mahmoud.bakingapp.Utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.mahmoud.bakingapp.Model.BakingDetail;
import com.example.mahmoud.bakingapp.Model.BakingIngrediant;
import com.example.mahmoud.bakingapp.Model.BakingSteps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmoud on 17/12/17.
 */

public class BakingUtils  {


    private static final String LOG_MEG =BakingUtils.class.getSimpleName();

    private static List<BakingDetail> extractBaking(String bakingJson){
        if(TextUtils.isEmpty(bakingJson)){
            return null;
        }
        List<BakingDetail> bakingList = new ArrayList<>();
        String recipeCard;
        int servings;

        String ingrediantsMeasure = null;
        String ingrediantsIngredient= null;
        int ingrediantsQuantity= 0;

        int stepId=0;
        String stepShortDescription= null;
        String stepDescription=null;
        String stepVideoUrl=null;
        String stepThumbnailUrl=null;



        try {
            JSONArray jsonRoot = new JSONArray(bakingJson);
            for(int i=0;i<jsonRoot.length();i++){
                List<BakingIngrediant> ingrediantList = new ArrayList<>();
                List<BakingSteps> stepsList = new ArrayList<>();

                JSONObject jsonObject = jsonRoot.getJSONObject(i);
                recipeCard = jsonObject.getString("name");
                servings = jsonObject.getInt("servings");
               // bakingList.add(new BakingDetail(name));
                JSONArray ingrediants = jsonObject.getJSONArray("ingredients");
                JSONArray steps = jsonObject.getJSONArray("steps");
                for(int k=0;k<ingrediants.length();k++){
                    JSONObject ingrediantObject = ingrediants.getJSONObject(k);
                    ingrediantsQuantity = ingrediantObject.getInt("quantity");
                    ingrediantsMeasure = ingrediantObject.getString("measure");
                    ingrediantsIngredient = ingrediantObject.getString("ingredient");

                    ingrediantList.add(
                            new BakingIngrediant(ingrediantsMeasure,
                                    ingrediantsIngredient,
                                    ingrediantsQuantity)
                    );

                }
                for(int k = 0;k<steps.length();k++){
                    JSONObject stepObject = steps.getJSONObject(k);
                    stepId = stepObject.getInt("id");
                    stepShortDescription = stepObject.getString("shortDescription");
                    stepDescription= stepObject.getString("description");
                    stepVideoUrl = stepObject.getString("videoURL");
                    stepThumbnailUrl = stepObject.getString("thumbnailURL");

                    stepsList.add(
                            new BakingSteps(stepId,
                                    stepShortDescription,
                                    stepDescription,
                                    stepVideoUrl,
                                    stepThumbnailUrl)
                    );
                    //Log.i(LOG_MEG,stepsList.get(k).getStepDescription());
                }
                bakingList.add(
                        new BakingDetail(
                                recipeCard,
                                servings,
                                ingrediantList,
                                stepsList
                        )
                );
               //ingrediantList.clear();
               //stepsList.clear();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bakingList;
    }
    private static URL createURl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = null;
        if(url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;


        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    public static List<BakingDetail> fetchBakingData(String requestUrl){
        URL url = createURl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<BakingDetail> bakingList = extractBaking(jsonResponse);
        //Log.i(LOG_MEG,bakingList.get(1).getStepsList().get(2).getStepShortDescription());
        return bakingList;
    }
}
