package com.it_lab.teleport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by alex on 13.07.15.
 */
public class Request {

    private String teg;
    private String uri;

    public String getTeg() {
        return teg;
    }

    public void setTeg(String teg) {
        this.teg = teg;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Request(String teg, String uri) {

        this.teg = teg;
        this.uri = uri;
    }



    public JSONObject getJOSON()
    {
        JSONObject json=new JSONObject();
        try {
            json.put("TAG", teg);
            json.put("URI", uri);
        }
        catch (JSONException e) {
                e.printStackTrace();
        }

        return json;

        }





}