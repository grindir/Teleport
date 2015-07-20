package com.it_lab.teleport;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import io.vov.vitamio.MediaPlayer;
import android.widget.TextView;
import io.vov.vitamio.widget.VideoView;


public class VideoPlayActivity extends ActionBarActivity {

    private VideoView videoView;
    private String uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        TextView videoName=(TextView) findViewById(R.id.videoName);
        videoName.setText(getIntent().getSerializableExtra("TAG").toString());
        uri=getIntent().getSerializableExtra("URI").toString();



    }


}
