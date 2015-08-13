package com.it_lab.teleport;



import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import io.vov.vitamio.LibsChecker;


import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import static com.it_lab.teleport.R.*;


public class VideoPlayActivity extends ActionBarActivity {

    private VideoView videoView;
    private String uri;
    ProgressDialog mProgressDialog;



    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);


        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(layout.activity_video_play);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getIntent().getSerializableExtra("TAG").toString());

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // устанавливаем стиль
        mProgressDialog.setMessage("Загружаю. Подождите...");  // задаем текст
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();


        uri=getIntent().getSerializableExtra("URI").toString();

        videoView = (VideoView) findViewById(id.videoView);
        videoView.setVideoURI(Uri.parse(uri));
        videoView.requestFocus();
        videoView.setBufferSize(1);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mProgressDialog.dismiss();
            }
        });


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            actionBar.hide();
            videoView.setMediaController(new MediaController(this));
        }
        else
        {

            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    ImageView imageView=(ImageView) findViewById(id.imageView);
                    imageView.setImageDrawable(getResources().getDrawable( drawable.bigpause));
                    if(videoView.isPlaying()) {
                        videoView.pause();
                        imageView.setVisibility(View.VISIBLE);
                    }
                    else {

                        imageView.setVisibility(View.INVISIBLE);
                        videoView.start();
                    }
                    return false;
                }
            });
        }



    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId())
        {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



