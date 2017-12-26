package com.example.mahmoud.bakingapp;


import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class StepFragment extends Fragment {


    private static final String LOG_MSG = StepFragment.class.getSimpleName();
    private static final String BAKE_NAME = "baking-name    ";
    SimpleExoPlayerView playerView;
    SimpleExoPlayer player;

    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;

    TextView mStepDescription;
    String mVideoUrl ;
    BakingDetail bakingObject;
    int position;
    int pos;

    public StepFragment() {
        // Required empty public constructor
    }

    public static StepFragment newInstance(BakingDetail mBakingDetail, int pos) {
        Bundle args = new Bundle();
        
        StepFragment fragment = new StepFragment();
        args.putParcelable(BAKE_NAME,mBakingDetail);
        args.putInt("pos",pos);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bakingObject = getArguments().getParcelable(BAKE_NAME);
            position = getArguments().getInt("pos");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_step,container,false);

        Log.i(LOG_MSG,"hehehehhe");

       // bakingObject = getActivity().getIntent().getParcelableExtra("object");

        //position = getActivity().getIntent().getIntExtra("position",0);

        mVideoUrl = bakingObject.getStepsList().get(position).getStepVideoUrl();

        playerView = (SimpleExoPlayerView)rootView.findViewById(R.id.video_step);

        if(mVideoUrl == null || mVideoUrl.equals("") ){
            playerView.setVisibility(View.GONE);
        }

        mStepDescription = (TextView)rootView.findViewById(R.id.tv_step_description);
        mStepDescription.setText(bakingObject.getStepsList().get(position).getStepDescription());

        return rootView;
    }

    private void initializePlayer(){
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(),new DefaultLoadControl()
        );

        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow,playbackPosition);


        Uri uri = Uri.parse(mVideoUrl);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource,true,false);
    }

    private MediaSource buildMediaSource(Uri uri){
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(),null,null
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        if(Util.SDK_INT > 23){
            initializePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideSystemUi();
        if((Util.SDK_INT <= 23 || player == null)){
            releasePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if((Util.SDK_INT <= 23 || player == null)){
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(Util.SDK_INT <= 23){
            releasePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }
}
