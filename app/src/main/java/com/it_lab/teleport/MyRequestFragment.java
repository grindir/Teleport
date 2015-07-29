package com.it_lab.teleport;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 27.07.15.
 */
public class MyRequestFragment extends Fragment {
    ListView listView;
    List<Request> myRequest;
    SharedPreferences sharedPreferences;
    AQuery aQuery;
    RequestAdapter adapter;

    @Override
    public void onPause() {

        Request.saveList(sharedPreferences,myRequest);
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_request_fragment, container, false);
        sharedPreferences=inflater.getContext().getSharedPreferences("SaveMyRequest", Context.MODE_PRIVATE);
        myRequest=new ArrayList<>();
        aQuery=new AQuery(getActivity());
        listView = (ListView) view.findViewById(R.id.listView);
        initData(getActivity().getIntent());
        adapter = new RequestAdapter(inflater.getContext(), myRequest, R.layout.item_my_reguest);
        listView.setAdapter(adapter);
        update();






        return view;

    }


    private void update()
    {
        String url = "http://192.168.0.238:8080";
        JSONObject input = new JSONObject();
        try {
            input.put("hello", "world");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        aQuery.post(url, input, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {

                Toast.makeText(aQuery.getContext(), "отправлен", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initData(Intent intent) {

//        myRequest.add(new Request("", "begin"));
//       myRequest.add(new Request("DemoDay", "rtsp://s-projects.ru:1935/live/android_test"));
//       myRequest.add(new Request("Тестовый поток", "rtsp://s-projects.ru:1935/live/test.stream/playlist.m3u8\""));
//       myRequest.add(new Request("", "next"));
//       myRequest.add(new Request("DemoDay", "rtsp://s-projects.ru:1935/live/android_test"));
//       myRequest.add(new Request("DemoDay", "url"));
        Request.getSaveList(sharedPreferences, myRequest);
        if(intent.getAction().equals("addMyRequest"))
        {
            myRequest.add(1,new Request(intent.getSerializableExtra("TAG").toString(),""));
        }




    }
}