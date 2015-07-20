package com.it_lab.teleport;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MyRequestActivity extends ActionBarActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request);

        listView=(ListView) findViewById(R.id.listViewMyRequest);
        MyRequestAdapter adapter=new MyRequestAdapter(this,initData());
        listView.setAdapter(adapter);
    }
    private List<Request> initData()
    {
        List<Request> list=new ArrayList<>();
        list.add(new Request("#сурскоеморе", ""));
        list.add(new Request("#паркбелинского", ""));

        return list;
    }


}
