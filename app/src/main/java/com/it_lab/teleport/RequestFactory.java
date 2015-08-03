package com.it_lab.teleport;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 02.08.15.
 */
public class RequestFactory {

    private List<Request> list;
    boolean log;
    SharedPreferences mSettings;

    public RequestFactory(Context context,String nameSaveFile) {

        list = new ArrayList<>();
        list.add(new Request("", "begin"));
        log=true;
        mSettings=context.getSharedPreferences(nameSaveFile, Context.MODE_PRIVATE);
    }



    public void add(String tag,String uri)
    {
        if(log)
        {
            log=false;
            list.add(new Request("","next"));
        }
        list.add(new Request(tag,uri));

    }

    public void addPersonal(String tag,String uri){
        list.add(1, new Request(tag, uri));

    }

    public int size()
    {
        return list.size();
    }

    public Request get(int id){
        return list.get(id);
    }

    public void getSaveList() {
        String str[];
        if (mSettings.contains("SIZE")) {
            for (int i = 1; i < Integer.parseInt(mSettings.getString("SIZE", "")); i++) {
                str = mSettings.getString("" + i, "").split(" ");
                if (str.length < 2)
                    list.add(new Request(str[0], ""));
                else
                    list.add(new Request(str[0], str[1]));

            }
        }
    }
    public void remove(int id)
    {
        list.remove(id);
    }

    public void addAll(List<Request> list)
    {
        this.list.addAll(list);

    }

     public void clean()
    {
        list.clear();
        list.add(new Request("", "begin"));
    }

     public void saveList() {


        SharedPreferences.Editor editor = mSettings.edit();

         editor.putString("SIZE", "" + list.size());
        Request request;
        for (int i = 1; i < list.size(); i++) {
            request = list.get(i);
            editor.putString("" + i, request.getTeg() + " " + request.getUri());
        }
        editor.apply();
    }
}
