package com.it_lab.teleport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 31.07.15.
 */
public class Transformer {


    public static JSONObject getJSON(String key ,Request request)
    {
        JSONObject json=new JSONObject();

        try {
            json.put(key,request.getJOSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    public static  List<Request> getList(JSONArray array)
    {
        List<Request> list =new ArrayList<>();
        Request request;
        try {

            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = (JSONObject) array.get(i);
                request = new Request(jsonObject.get("TAG").toString(), jsonObject.get("URI").toString());
                list.add(request);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static JSONObject getJSON(String key ,List<Request> list)
    {
        JSONArray array= new JSONArray();
        for(Request request: list)
        {
            array.put(request.getJOSON());
        }

        JSONObject json=new JSONObject();
        try {
            json.put(key,array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
