package com.it_lab.teleport;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 27.07.15.
 */
public class MyRequestFragment extends Fragment {
    ListView listView;
    RequestFactory myRequest;
    HTTPClient client;
    RequestAdapter adapter;

    @Override
    public void onPause() {

        myRequest.saveList();
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_request_fragment, container, false);
        myRequest = new RequestFactory(inflater.getContext(),"SaveMyRequest");
        adapter = new RequestAdapter(inflater.getContext(), myRequest, R.layout.item_my_reguest);

        client=new HTTPClient(getActivity().getApplicationContext(),myRequest,adapter);
        initData(getActivity().getIntent());

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);




        return view;

    }

    private void initData(Intent intent) {

         myRequest.getSaveList();

        if (intent.getAction().equals("addMyRequest")) {

            Request request=new Request(intent.getSerializableExtra("TAG").toString(), " ");
            client.add(request);


        }
        else
        {
            client.getList();

        }

    }
}