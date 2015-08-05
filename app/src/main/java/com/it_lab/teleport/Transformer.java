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


    public static Request getRequest(JSONObject json)
    {
        Request request=new Request("","",0,"");
        try {
            request=new Request(json.get("TAG").toString(),json.get("URI").toString(),Integer.parseInt(json.get("ID").toString()), (String) json.get("LOGIN"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return request;
    }

    public static  List<Request> getList(JSONArray array)
    {
        List<Request> list =new ArrayList<>();
        Request request;
        try {
        for (int i = 0; i < array.length(); i++) {

            JSONObject jsonObject = null;

                jsonObject = (JSONObject) array.get(i);

            request = new Request(jsonObject.get("TAG").toString(), jsonObject.get("URI").toString(),Integer.parseInt(jsonObject.get("ID").toString()),jsonObject.get("LOGIN").toString());
            list.add(request);
            }} catch (JSONException e) {
                e.printStackTrace();
            }

        return list;
    }


}
