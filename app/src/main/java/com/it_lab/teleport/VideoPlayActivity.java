package com.it_lab.teleport;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class VideoPlayActivity extends ActionBarActivity {

    private VideoView videoView;
    private String uri;
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);


        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video_play);
        toolbar = (Toolbar) findViewById(R.id.appbarVideo);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //TextView videoName=(TextView) findViewById(R.id.videoName);
        //videoName.setText(getIntent().getSerializableExtra("TAG").toString());

        actionBar.setTitle(getIntent().getSerializableExtra("TAG").toString());
        uri=getIntent().getSerializableExtra("URI").toString();

        videoView = (VideoView) findViewById(R.id.videoView);

        if (uri == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(VideoPlayActivity.this, "Please edit VideoViewDemo Activity, and set path" + " variable to your media file URL/path", Toast.LENGTH_LONG).show();
            return;
        } else {
            videoView.setVideoURI(Uri.parse(uri));
            videoView.setMediaController(new MediaController(this));
            videoView.requestFocus();
            videoView.start();

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



