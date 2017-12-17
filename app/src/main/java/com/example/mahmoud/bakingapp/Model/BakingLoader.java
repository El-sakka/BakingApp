package com.example.mahmoud.bakingapp.Model;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.mahmoud.bakingapp.BakingDetail;
import com.example.mahmoud.bakingapp.BakingUtils;

import java.util.List;

public class BakingLoader extends AsyncTaskLoader<List<BakingDetail>> {
    private static final String LOG_MEG = BakingLoader.class.getSimpleName();
    String mUrl;
    public BakingLoader(Context context,String mUrl){
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<BakingDetail> loadInBackground() {
        if(mUrl == null || mUrl.length()<1){
            return null;
        }
        List<BakingDetail> bakingList = BakingUtils.fetchBakingData(mUrl);
        //Log.i(LOG_MEG,bakingList.get(0).getRecipeCard());

        return bakingList;
    }

}
