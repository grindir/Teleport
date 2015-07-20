package com.it_lab.teleport;


import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {


    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView=(ListView) findViewById(R.id.listView);
        DumpAdapter adapter=new DumpAdapter(this,initData());
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private List<Request> initData()
    {
        List<Request> list=new ArrayList<>();
        list.add(new Request("#demoDay", "http://s-projects.ru:8110/hls/stream2.m3u8"));
        list.add(new Request("#тестовыйпоток", "http://s-projects.ru:8110/hls/stream1.m3u8"));

        return list;
    }

    public void openRequestMe(View view)
    {
        Intent intent=new Intent(this,RequestMeActivity.class);
        startActivity(intent);
    }
    public void openMyRequest(View view)
    {
        Intent intent=new Intent(this,MyRequestActivity.class);
        startActivity(intent);
    }

    public void startStream(View view)
    {
        Intent intent=new Intent(this,Stream.class);
        startActivity(intent);
    }



}
