package com.it_lab.teleport;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;


public class VideoPlayActivity extends Activity {

    private VideoView videoView;
    private String uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        TextView videoName=(TextView) findViewById(R.id.videoName);
        videoName.setText(getIntent().getSerializableExtra("TAG").toString());
        uri=getIntent().getSerializableExtra("URI").toString();

        videoView=(VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();

    }


}
