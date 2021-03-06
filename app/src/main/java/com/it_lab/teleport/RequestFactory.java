package com.it_lab.teleport;

import android.content.Context;
import android.content.SharedPreferences;


import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 02.08.15.
 */
public class RequestFactory {

    private List<Request> list;
    boolean flag;
    boolean flag2;
    SharedPreferences mSettings;

    public RequestFactory(Context context,String nameSaveFile) {

        list = new ArrayList<>();
        flag=true;
        flag2=true;
        mSettings=context.getSharedPreferences(nameSaveFile, Context.MODE_PRIVATE);
    }


    public void add(Request request)
    {
        if(flag)
        {
            flag=false;
            list.add(new Request("","next",0,"system"));
        }
        list.add(request);
    }
    public void add(String tag,String uri,long id,String autor)
    {
        if(flag)
        {
            flag=false;
            list.add(new Request("","next",0,"system"));
        }
        list.add(new Request(tag,uri,id,autor));

    }

    public void addPersonal(Request request){
        if(flag2)
        {
            flag2=false;
            list.add(0,new Request("","begin",0,"system"));
        }
        list.add(1, request);

    }
    public void addPersonal(String tag,String uri,long id,String autor){
        if(flag2)
        {
            flag2=false;
            list.add(0,new Request("","begin",0,"system"));
        }
        list.add(1, new Request(tag, uri,id,autor));

    }

    public int size()
    {
        return list.size();
    }

    public Request get(int id){
        return list.get(id);
    }

    public void getSaveList() {
        JSONObject json;
        JSONParser parser=new JSONParser();

        if (mSettings.contains("SIZE")) {
            try {
            for (int i = 1; i < Integer.parseInt(mSettings.getString("SIZE", "")); i++) {

                    json = (JSONObject) parser.parse(mSettings.getString("" + i, ""));
                    list.add(Transformer.getRequest(json));

                }


            }catch (ParseException e) {
                e.printStackTrace();


            }
        }
    }
    public void remove(int id)
    {
        list.remove(id);
    }

    public void addAll2(List<Request> list)
    {
        for(Request request:list)
        {
            if(request.getUri().equals(" "))
            {
                add(request);
            }

        }

    }

    public void addAll(List<Request> list)
    {
        for(Request request:list)
        {
            if(request.getAutor().equals(User.login))
            {
                if(!(request.getUri().contains(User.login)))
                    addPersonal(request);
            }
            else
                if(!(request.getUri().equals(" ")))
                    add(request);
        }


    }

     public void clean()
    {
        list.clear();
        flag=true;
        flag2=true;
    }


     public void saveList() {


        SharedPreferences.Editor editor = mSettings.edit();

         editor.putString("SIZE", "" + list.size());
        Request request;
        for (int i = 1; i < list.size(); i++) {
            request = list.get(i);
            editor.putString("" + i, request.getJOSON().toString());
        }
        editor.apply();
    }
}
