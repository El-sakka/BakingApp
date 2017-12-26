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
    BakingDetail bakingObject;
    StepFragmentPagerAdapter mAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);


        bakingObject = getIntent().getParcelableExtra("object");
        int posotion = getIntent().getIntExtra("position",0);

        //Intent intent = new Intent(this,StepFragment.class);
        int Count  = bakingObject.getStepsList().size();

        Log.i(LOG_MSG,posotion+"");
        mViewPager = findViewById(R.id.viewPager);


        mAdapter = new StepFragmentPagerAdapter(this,getSupportFragmentManager(),Count,bakingObject,posotion);

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(posotion);

        TabLayout tabLayout =(TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }



}
