package com.it_lab.teleport;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class AddRequestActivity extends ActionBarActivity {

    Toolbar toolbar;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_request);
        toolbar = (Toolbar) findViewById(R.id.appbarAdd);
        setSupportActionBar(toolbar);
        editText=(EditText) findViewById(R.id.editText);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId())
        {
            case R.id.action_settings:
                return true;
            case R.id.send:
                Intent intent=new Intent(this,MainActivity.class);
                intent.setAction("addMyRequest");
                intent.putExtra("TAG",editText.getText().toString());
                startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
