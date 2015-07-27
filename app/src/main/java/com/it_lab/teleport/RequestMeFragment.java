package com.it_lab.teleport;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 27.07.15.
 */
public class RequestMeFragment extends Fragment {
    ListView listView;
    List<Request> requestMe;
    SharedPreferences sharedPreferences;


    @Override
    public void onPause() {

        Request.saveList(sharedPreferences, requestMe);
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.request_me_fragment, container, false);
        sharedPreferences=inflater.getContext().getSharedPreferences("SaveRequestMe", Context.MODE_PRIVATE);
        requestMe=new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.listView2);
        initData();
        RequestAdapter adapter = new RequestAdapter(inflater.getContext(), requestMe, R.layout.item_request_me);
        listView.setAdapter(adapter);

        return view;

    }


    private void initData() {

//        requestMe.add(new Request("", "begin"));
//        requestMe.add(new Request("#demoDay", ""));
//        requestMe.add(new Request("#тестовыйпоток", ""));
//        requestMe.add(new Request("", "next"));
//        requestMe.add(new Request("#demoDay", ""));
//        requestMe.add(new Request("#Птичка", ""));
//        requestMe.add(new Request("#Речка", ""));
        Request.getSaveList(sharedPreferences, requestMe);




    }
}