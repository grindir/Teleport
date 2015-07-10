package com.it_lab.teleport;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity extends Activity {

    VideoView videoView;
    Uri playingUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playingUri= Uri.parse("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov");
        setContentView(R.layout.activity_main);
        videoView=(VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(playingUri);
        MediaController mMediaController = new MediaController(this);
        mMediaController.setMediaPlayer(videoView);
        mMediaController.setAnchorView(videoView);
        videoView.setMediaController(mMediaController);
        videoView.requestFocus(0);
        videoView.start();


    }




}
