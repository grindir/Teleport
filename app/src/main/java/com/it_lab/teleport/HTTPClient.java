package com.it_lab.teleport;

import android.content.Context;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by alex on 31.07.15.
 */
public class HTTPClient {
    String url = "http://192.168.0.238:8080";
    AQuery aQuery;
    RequestFactory factory;
    RequestAdapter adapter;


    public HTTPClient(Context context,RequestFactory factory,RequestAdapter adapter) {
        aQuery=new AQuery(context);
        this.factory=factory;
        this.adapter=adapter;


    }




    public void  remove(int id)
    {
        JSONObject json=new JSONObject();
        try {
            json.put("REQUEST","DELOBJECT");
            json.put("ID", id);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        aQuery.setSelection(1);
        aQuery.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {


            }});

    }

    public void add(Request request)
    {
        JSONObject json=new JSONObject();

        try {
            json.put("REQUEST","ADDOBJECT");
            json.put("OBJECT", request.getJOSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        aQuery.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {

                    getList();

            }});


    }
    public void getList()
    {
        JSONObject json = new JSONObject();

        try {
            json.put("REQUEST", "GETLIST");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        aQuery.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {

                if(json!=null) {
                    factory.clean();
                    JSONArray array=new JSONArray();
                    try {
                         array=json.getJSONArray("ARRAY");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    factory.addAll(Transformer.getList(array));
                    adapter.notifyDataSetChanged();

                }

            }});


    }


}
