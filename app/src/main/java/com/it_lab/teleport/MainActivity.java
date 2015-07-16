package com.it_lab.teleport;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity  {

    final String LOG_TAG = "myLogs";
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView=(ListView) findViewById(R.id.listView);
        DataAdapter adapter=new DataAdapter(this,initData());
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private List<Data> initData()
    {
        List<Data> list=new ArrayList<>();
        list.add(new Data("#сурскоеморе", ""));
        list.add(new Data("#паркбелинского",""));
        list.add(new Data("#эйфелеваябашня", ""));

        return list;
    }


    public void startStream(View view)
    {
        Intent intent=new Intent(this,Stream.class);
        startActivity(intent);
    }



}
