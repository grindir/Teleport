package com.it_lab.teleport;


import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {


    ListView listView;
    ListView listView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView) findViewById(R.id.listView);
        RequestAdapter adapter=new RequestAdapter(this,initData(),R.layout.item_my_reguest);
        listView.setAdapter(adapter);

        listView2=(ListView) findViewById(R.id.listView2);
        RequestAdapter adapter2=new RequestAdapter(this,initData2(),R.layout.item_request_me);
        listView2.setAdapter(adapter2);

        createTab();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private List<Request> initData()
    {
        List<Request> list=new ArrayList<>();
        list.add(new Request("","begin"));
        list.add(new Request("#demoDay", "http://192.168.0.210:80/myapp/mystream"));
        list.add(new Request("#тестовыйпоток", "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"));
        list.add(new Request("","next"));
        list.add(new Request("#demoDay", "http://192.168.0.210:80/myapp/mystream"));
        list.add(new Request("#demoDay", "http://192.168.0.210:80/myapp/mystream"));
        list.add(new Request("#demoDay", "http://192.168.0.210:80/myapp/mystream"));


        return list;
    }

    private List<Request> initData2()
    {
        List<Request> list=new ArrayList<>();
        list.add(new Request("","begin"));
        list.add(new Request("#demoDay", ""));
        list.add(new Request("#тестовыйпоток", ""));
        list.add(new Request("","next"));
        list.add(new Request("#demoDay", ""));
        list.add(new Request("#Птичка", ""));
        list.add(new Request("#Речка", ""));


        return list;
    }



    public void startStream(View view)
    {
        Intent intent=new Intent(this,Stream.class);
        startActivity(intent);
    }

    //ПРОВЕРЕН
    private void createTab(){

        TabHost tabs = (TabHost) findViewById(R.id.tabHost);

        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tag1");

        spec.setContent(R.id.tab1);
        spec.setIndicator("Посмотреть");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Показать");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }


}
