package com.it_lab.teleport;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity  {

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




}
