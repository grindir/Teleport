package com.it_lab.teleport;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import android.widget.TextView;
import android.widget.Toast;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class VideoPlayActivity extends ActionBarActivity {

    private VideoView videoView;
    private String uri;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);


        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video_play);

        TextView videoName=(TextView) findViewById(R.id.videoName);
        videoName.setText(getIntent().getSerializableExtra("TAG").toString());
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

        }
    }


}
