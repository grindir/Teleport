package com.it_lab.teleport;


import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by alex on 27.07.15.
 */
public class MyRequestFragment extends Fragment {
    ListView listView;
    static RequestFactory myRequest;
    static HTTPClient client;
    static RequestAdapter adapter;

    @Override
    public void onPause() {

//        myRequest.saveList();
        super.onPause();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.my_request_fragment, container, false);
        myRequest = new RequestFactory(inflater.getContext(),"SaveMyRequest");
        adapter = new RequestAdapter(inflater.getContext(), myRequest, R.layout.item_my_reguest);

        client=new HTTPClient(getActivity().getApplicationContext(),myRequest,adapter);
        initData(getActivity().getIntent());

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        return view;

    }
    public static void update()
    {
        client.getList();
    }

    private void initData(Intent intent) {

//         myRequest.getSaveList();

        if (intent.getAction().equals("addMyRequest")) {

            Request request=new Request(intent.getSerializableExtra("TAG").toString()," ",0, User.login);
            client.add(request);

        }
        else
        {
            client.getList();

        }

    }





    public static void search(String newText)
    {
        Request request;
        RequestFactory factory=new RequestFactory(MainActivity.context,"Save");
        for(int i=1;i<myRequest.size();i++)
        {
            request=myRequest.get(i);
            if(request.getTeg().toLowerCase().contains(newText.toLowerCase())&&!(request.getUri().equals("next")))
                if(request.getAutor().equals(User.login))
                    factory.addPersonal(request);
                else
                    factory.add(request);

        }
        adapter.setData(factory);
        adapter.notifyDataSetChanged();
    }
}