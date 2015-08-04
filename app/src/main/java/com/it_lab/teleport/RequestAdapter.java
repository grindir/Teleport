package com.it_lab.teleport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by alex on 13.07.15.
 */
public class RequestAdapter extends BaseAdapter {
    private RequestFactory factory;
    private LayoutInflater inflater;
    private int itemID;
    private boolean flagDump;
    private HTTPClient client;


    public RequestAdapter(Context context, RequestFactory factory,int itemID) {

        this.factory=factory;
        this.itemID =itemID;
        flagDump=false;
        client=new HTTPClient(context,factory,this);
        inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return factory.size();
    }

    @Override
    public Object getItem(int position) {
        return factory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=convertView;
        final Request request = getRequest(position);
        TextView textView;
        ImageButton button;



        if(view ==null)
        {
            view=inflater.inflate(itemID,parent,false);
        }

        switch (itemID)
        {

            case R.layout.item_my_reguest:


                textView= (TextView) view.findViewById(R.id.myRequestName);
                button=(ImageButton) view.findViewById(R.id.delete);

                if(request.getUri().equals("begin")) {
                    textView.setText("Мои запросы");
                    button.setVisibility(View.INVISIBLE);
                    button=(ImageButton) view.findViewById(R.id.wathc);
                    button.setVisibility(View.INVISIBLE);
                    flagDump=false;
                    return  view;

                }
                else
                    if(request.getUri().equals("next"))
                    {
                        textView.setText("Свалка");
                        button.setVisibility(View.INVISIBLE);
                        button=(ImageButton) view.findViewById(R.id.wathc);
                        button.setVisibility(View.INVISIBLE);
                        flagDump=true;
                        return  view;
                    }
                     else
                        textView.setText(request.getTeg());

                if(flagDump)
                    button.setVisibility(View.INVISIBLE);
                else
                    button.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            remove(position);
                        }
                    });


                button=(ImageButton) view.findViewById(R.id.wathc);
                button.setVisibility(View.VISIBLE);
                if(!flagDump& request.getUri().equals(" "))
                    button.setEnabled(false);
                else
                    button.setEnabled(true);

                button.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                            jump(VideoPlayActivity.class, request);
                    }
                });






                break;

            case R.layout.item_request_me:

                textView= (TextView) view.findViewById(R.id.requestsMeName);
                button=(ImageButton) view.findViewById(R.id.unsubscribe);


                if(request.getUri().equals("begin")) {
                    textView.setText("Запросы мне");
                    button.setVisibility(View.INVISIBLE);
                    button=(ImageButton) view.findViewById(R.id.agree);
                    button.setVisibility(View.INVISIBLE);
                    flagDump=false;
                    return  view;

                }
                else
                if(request.getUri().equals("next"))
                {
                    textView.setText("Свалка");
                    button.setVisibility(View.INVISIBLE);
                    button=(ImageButton) view.findViewById(R.id.agree);
                    button.setVisibility(View.INVISIBLE);
                    flagDump=true;
                    return  view;
                }
                else
                    textView.setText(request.getTeg());

                if(flagDump)
                    button.setVisibility(View.INVISIBLE);
                else
                    button.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        remove(position);
                    }
                });


                button=(ImageButton) view.findViewById(R.id.agree);
                button.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        jump(Stream.class, request);
                    }
                });

                button.setVisibility(View.VISIBLE);

                break;


        }


        return view;
    }



    private void jump(@Nullable Class activity, Request request)
    {
        Intent intent=new Intent(inflater.getContext(),activity);
        intent.putExtra("TAG", request.getTeg());
        intent.putExtra("URI", request.getUri());
        inflater.getContext().startActivity(intent);

    }
    private void remove(int id)
    {
        factory.remove(id);
        notifyDataSetChanged();
        client.remove(id-1);


    }

    private Request getRequest(int position)
    {
        return (Request) getItem(position);
    }
}
