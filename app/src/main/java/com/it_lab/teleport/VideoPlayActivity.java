package com.it_lab.teleport;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class VideoPlayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        TextView view=(TextView) findViewById(R.id.textView2);
        view.setText(getIntent().getSerializableExtra("id").toString());
    }


}
