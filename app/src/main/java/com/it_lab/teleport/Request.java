package com.it_lab.teleport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

/**
 * Created by alex on 13.07.15.
 */
public class Request {

    public String teg;
    public String uri;

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

    static public void getSaveList(SharedPreferences mSettings, List<Request> list) {
        String str[];
        if (mSettings.contains("SIZE")) {
            for (int i = 0; i < Integer.parseInt(mSettings.getString("SIZE", "")); i++) {
                str = mSettings.getString("" + i, "").split(" ");
                if (str.length < 2)
                    list.add(new Request(str[0], ""));
                else
                    list.add(new Request(str[0], str[1]));

            }
        }
    }
    static public void cleanList(List<Request> list)
    {
        list.clear();
        list.add(new Request("","begin"));
    }



    static public void saveList(SharedPreferences mSettings , List<Request> list) {


        SharedPreferences.Editor editor = mSettings.edit();

        editor.putString("SIZE", "" + list.size());
        Request request;
        for (int i = 0; i < list.size(); i++) {
            request = list.get(i);
            editor.putString("" + i, request.getTeg() + " " + request.getUri());
        }
        editor.apply();
    }



}