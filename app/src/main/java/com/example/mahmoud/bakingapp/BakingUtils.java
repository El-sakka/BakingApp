package com.example.mahmoud.bakingapp;

import android.text.TextUtils;

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

    public static String BAKING_JSON= "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";



    private static List<BakingDetail> extractBaking(String bakingJson){
        if(TextUtils.isEmpty(bakingJson)){
            return null;
        }
        List<BakingDetail> bakingList = new ArrayList<>();

        try {
            JSONArray jsonRoot = new JSONArray(bakingJson);
            for(int i=0;i<jsonRoot.length();i++){
                JSONObject jsonObject = jsonRoot.getJSONObject(i);
                String name = jsonObject.getString("name");
                bakingList.add(new BakingDetail(name));
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
}
