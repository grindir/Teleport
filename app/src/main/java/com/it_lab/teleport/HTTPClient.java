package com.it_lab.teleport;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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
    static String url = "http://192.168.0.231:8080";
    AQuery aQuery;
    RequestFactory factory;
    RequestAdapter adapter;


    public HTTPClient(Context context,RequestFactory factory,RequestAdapter adapter) {
        aQuery=new AQuery(context);
        this.factory=factory;
        this.adapter=adapter;


    }
    public static void registr(final Context context)
    {
        final AQuery aQuery=new AQuery(context);
        JSONObject json=new JSONObject();
        try {
            json.put("REQUEST","REGISTRATION");
            json.put("LOGIN",User.login);
            json.put("USERNAME","");
            json.put("PASSWORD",User.password);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        aQuery.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                try {
                    if(json.get("Status").toString().equals("OK"));
                    {
                        Toast.makeText(context, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                        login(context);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }});
        return ;

    }


    public static void login(final Context context)
    {
        final AQuery aQuery=new AQuery(context);
        JSONObject json=new JSONObject();
        try {
            json.put("REQUEST","AUTHORIZATION");
            json.put("LOGIN",User.login);
            json.put("PASSWORD",User.password);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        aQuery.setSelection(1);
        aQuery.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {

                try {
                    if(json.get("Status").toString().equals("OK"))
                    {
                        User.Save();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }});

    }

    public static void  PushStream(Context context,Request request){
        AQuery aQuery=new AQuery(context);
        JSONObject json=new JSONObject();
        try {
            json.put("REQUEST","PUSHSTREAM");
            json.put("DEFENDANT",User.login);
            json.put("OBJECT",request.getJOSON());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        aQuery.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
            }});

    }




    public void  remove(long id)
    {
        adapter.setData(factory);
        JSONObject json=new JSONObject();
        try {
            json.put("REQUEST","DELOBJECT");
            json.put("ID", id);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        aQuery.post(url, json, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {


            }});

    }

    public void add(Request request)
    {
        JSONObject json=new JSONObject();
        adapter.setData(factory);
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
        adapter.setData(factory);
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
    public void getList2()
    {
        JSONObject json = new JSONObject();
        adapter.setData(factory);
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
                    factory.addAll2(Transformer.getList(array));
                    adapter.notifyDataSetChanged();

                }

            }});

    }


}