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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putBoolean("SAVE_PLAY_ONLINE_STATE", videoView.isPlaying());
        savedInstanceState.putString("SAVE_PLAY_VIDEO_CHANNEL", playingUri.toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
