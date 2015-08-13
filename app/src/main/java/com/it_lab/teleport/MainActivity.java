package com.it_lab.teleport;





import android.content.Context;
import android.content.Intent;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;



import android.support.v7.widget.SearchView;
import android.view.Gravity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements SearchView.OnQueryTextListener,ActionBar.TabListener {

    public static Context context;
     ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
         pager=(ViewPager)findViewById(R.id.pager);
        if(!User.loginin) {
            User.getSave();
            HTTPClient.login(this);
        }


        List<Fragment> fragments=new ArrayList<>();
        fragments.add(Fragment.instantiate(this, MyRequestFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, RequestMeFragment.class.getName()));

        final ActionBar bar=getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        pager.setOffscreenPageLimit(fragments.size());
        PagerAdapter pagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);

        bar.addTab(bar.newTab().setText("Посмотреть")
                .setTabListener(this));
        bar.addTab(bar.newTab().setText("Показать")
                .setTabListener(this));




        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                bar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });


    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    class PagerAdapter extends FragmentPagerAdapter  {

        private List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String str="";
            if(position==0)
                str="Посмотреть";
            else
                str="Показать";
            return str;
        }
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
        if(!User.loginin)
        {
            Toast toast = Toast.makeText(context, "Показывать могу только зарегестрированные пользователи",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else
        {
            Intent intent=new Intent(this,StreamSettings.class);
            startActivity(intent);
        }



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
