package com.it_lab.teleport;



import android.content.Intent;

import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;






public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.appbarMain);
        setSupportActionBar(toolbar);

        createTab();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.action_settings :
                Toast.makeText(getApplicationContext(), "Setting Pressed", Toast.LENGTH_LONG).show();
                break;
            case  R.id.action_go:
                Intent intent =new Intent(this,AddRequestActivity.class);
                startActivity(intent);
                break;
            case R.id.action_login:
                Intent intentLogin =new Intent(this,Login.class);
                startActivity(intentLogin);
                break;

        }

        return super.onOptionsItemSelected(item);

    }


    public void startStream(View view)
    {
//        Intent intent=new Intent(this,Stream.class);
//        startActivity(intent)


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


}
