package com.example.mahmoud.bakingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import com.example.mahmoud.bakingapp.Adapter.StepFragmentPagerAdapter;
import com.example.mahmoud.bakingapp.Model.BakingDetail;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepActivity extends AppCompatActivity {


    private static final String LOG_MSG = StepActivity.class.getSimpleName();
    private static final String BAKING_OBJECT = "baking-object";
    private static final String POS = "position";
    private static final String COUNT = "count";
    private static final String BUNDLE = "bundle";

    BakingDetail bakingObject;
    StepFragmentPagerAdapter mAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_fragment);

        Intent sentIntent = getIntent();
        Bundle bun = sentIntent.getBundleExtra(BUNDLE);
        bakingObject = bun.getParcelable(BAKING_OBJECT);
        int posotion = bun.getInt(POS);

        //Intent intent = new Intent(this,StepFragment.class);
       // int count  = bakingObject.getStepsList().size();

        StepFragmentPager fragmentPager = new StepFragmentPager();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BAKING_OBJECT,bakingObject);
        bundle.putInt(POS,posotion);
       // bundle.putInt(COUNT,count);
        fragmentPager.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.step_fragment_container,fragmentPager).commit();

        Log.i(LOG_MSG,posotion+"");/*
        mViewPager = findViewById(R.id.viewPager);


        mAdapter = new StepFragmentPagerAdapter(this,getSupportFragmentManager(),count,bakingObject,posotion);

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(posotion);

        TabLayout tabLayout =(TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);*/


    }



}
