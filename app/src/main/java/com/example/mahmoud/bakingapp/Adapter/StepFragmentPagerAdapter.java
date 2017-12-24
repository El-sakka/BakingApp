package com.example.mahmoud.bakingapp.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.mahmoud.bakingapp.Model.BakingDetail;
import com.example.mahmoud.bakingapp.StepFragment;

/**
 * Created by mahmoudelsakka on 24/12/17.
 */

public class StepFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String LOG_MSG = StepFragmentPagerAdapter.class.getSimpleName() ;
    private Context mContext;
    private BakingDetail mBakingObject;
    private int mCount ;
    int pos;
    public StepFragmentPagerAdapter(Context context, FragmentManager fm, int mCount , BakingDetail mBakingObject,int pos){
        super(fm);
        this.mContext = context;
        this.mCount = mCount;
        this.mBakingObject = mBakingObject;
        this.pos = pos;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i(LOG_MSG,pos+"");

        //  position = pos;
        if(position < mCount) {
            return StepFragment.newInstance(mBakingObject,position);
        }
        else{
            return null;
        }
    }

    @Override
    public int getCount() {
        return mCount;
    }
}
