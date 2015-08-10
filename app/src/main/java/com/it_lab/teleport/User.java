package com.it_lab.teleport;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alex on 05.08.15.
 */
public class User {
    public static String login;
    public static String username;
    public static String password;
    public static boolean loginin;
    public static void Save()
    {
        SharedPreferences sharedPreferences=MainActivity.context.getSharedPreferences("SaveUser",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login",login);
        editor.putString("password",password);
        editor.apply();
    }
    public static void getSave()
    {
        SharedPreferences sharedPreferences=MainActivity.context.getSharedPreferences("SaveUser",Context.MODE_PRIVATE);
        login=sharedPreferences.getString("login","");
        password=sharedPreferences.getString("password","");
    }
}

