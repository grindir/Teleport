package com.it_lab.teleport;



import android.content.Context;
import android.content.Intent;

import android.os.Bundle;


import android.support.v7.app.ActionBarActivity;


import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.TabHost;

import android.widget.Toast;






public class MainActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();

        if(!User.loginin) {
            User.getSave();
            HTTPClient.login(this);
        }

        createTab();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId())
        {
            case R.id.action_settings :
                Toast.makeText(getApplicationContext(), "Setting Pressed", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_login:
                intent =new Intent(this,Login.class);

                startActivity(intent);
                break;
            case  R.id.action_go:
                if(!User.loginin)
                {
                    Toast toast = Toast.makeText(context, "Добавлять запросы могут только зарегистрированные пользователи",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else {
                    intent = new Intent(this, AddRequestActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.action_update:
                MyRequestFragment.update();
                RequestMeFragment.update();
                break;

        }

        return super.onOptionsItemSelected(item);

    }

    public void startStream(View view)
    {
        Intent intent=new Intent(this,StreamSettings.class);
        startActivity(intent);


    }



    //ПРОВЕРЕН
    private void createTab(){

        TabHost tabs = (TabHost) findViewById(R.id.tabHost);

        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tag1");

        spec.setContent(R.id.tab1);
        spec.setIndicator("Посмотреть");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Показать");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        MyRequestFragment.search(query);
        RequestMeFragment.search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        MyRequestFragment.search(newText);
        RequestMeFragment.search(newText);
        return false;
    }
}
