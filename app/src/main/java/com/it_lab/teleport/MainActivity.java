package com.it_lab.teleport;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {


    ListView listView;
    ListView listView2;
    TabHost tabs;
    ViewPager pager;
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

        pager = (ViewPager) findViewById(R.id.pager);

        createTab();

        pager.setAdapter(new MyPagerAdapter(this));
        pager.setOnPageChangeListener(this);
        tabs.setOnTabChangedListener(this);

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
        list.add(new Request("", "next"));
        list.add(new Request("#demoDay", "http://192.168.0.210:80/myapp/mystream"));
        list.add(new Request("#тестовыйпоток", "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"));
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
    public void createTab(){

        tabs = (TabHost) findViewById(R.id.tabHost);

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

    @Override
    public void onTabChanged(String tabId){
        int pageNumber = 0;
        if(tabId.equals("tab1")){
            pageNumber = 0;
        } else{
            pageNumber = 1;
        }
        pager.setCurrentItem(pageNumber);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int pageNumber) {
        tabs.setCurrentTab(pageNumber);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
