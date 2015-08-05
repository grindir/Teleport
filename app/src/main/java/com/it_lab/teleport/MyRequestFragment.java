package com.it_lab.teleport;

import android.app.Fragment;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.widget.SearchView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;







/**
 * Created by alex on 27.07.15.
 */
public class MyRequestFragment extends Fragment implements SearchView.OnQueryTextListener{
    ListView listView;
    RequestFactory myRequest;
    HTTPClient client;
    RequestAdapter adapter;

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Request request;
        RequestFactory factory=new RequestFactory(getActivity(),"Save");

        for(int i=1;i<myRequest.size();i++)
        {
            request=myRequest.get(i);
            if(request.getTeg().equals(query))
                factory.add(request.getTeg(),request.getUri(),request.getId(),request.getAutor());

        }
        adapter.setData(factory);
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}