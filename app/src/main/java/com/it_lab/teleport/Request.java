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
    private long id;
    private String autor;

    public Request(String teg, String uri, long id, String autor) {
        this.teg = teg;
        this.uri = uri;
        this.id = id;
        this.autor = autor;
    }

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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public JSONObject getJOSON()
    {
        JSONObject json=new JSONObject();
        try {
            json.put("TAG", teg);
            json.put("URI", uri);
            json.put("ID",id);
            json.put("LOGIN",autor);

        }
        catch (JSONException e) {
                e.printStackTrace();
        }

        return json;

        }





}