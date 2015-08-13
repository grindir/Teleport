package com.it_lab.teleport;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 27.07.15.
 */
public class RequestMeFragment extends Fragment {
    ListView listView;
    static RequestFactory requestMe;
    SharedPreferences sharedPreferences;
    static HTTPClient client;
    static RequestAdapter adapter;

    @Override
    public void onPause() {

//       Request.saveList(sharedPreferences, requestMe);
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.request_me_fragment, container, false);
        sharedPreferences=inflater.getContext().getSharedPreferences("SaveRequestMe", Context.MODE_PRIVATE);
        requestMe=new RequestFactory(inflater.getContext(),"SaveRequestMe");

        adapter = new RequestAdapter(inflater.getContext(), requestMe, R.layout.item_request_me);

        listView = (ListView) view.findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        client=new HTTPClient(getActivity(),requestMe,adapter);
        initData();


        return view;

    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//
//        switch (item.getItemId())
//        {
//
//            case R.id.action_update:
//                client.getList2();
//        }
//
//        return super.onOptionsItemSelected(item);
//
//    }


    private void initData() {



        client.getList2();


    }
    public static void update()
    {
        client.getList2();
    }
    public static void search(String newText)
    {
        Request request;
        RequestFactory factory=new RequestFactory(MainActivity.context,"Save");
        for(int i=1;i<requestMe.size();i++)
        {
            request=requestMe.get(i);
            if(request.getTeg().toLowerCase().contains(newText.toLowerCase())&&!(request.getUri().equals("next")))
                factory.add(request);

        }
        adapter.setData(factory);
        adapter.notifyDataSetChanged();
    }


}