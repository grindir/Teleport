package com.it_lab.teleport;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alex on 17.07.15.
 */
public class RequestMeAdapter extends BaseAdapter {
    private List<Request> list;
    private LayoutInflater inflater;

    public RequestMeAdapter(Context context, List<Request> list) {
        this.list = list;
        inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view ==null)
        {
            view=inflater.inflate(R.layout.item_request_me,parent,false);
        }

        TextView textView= (TextView) view.findViewById(R.id.requestsMeName);
        final Request request = getRequest(position);
        final int i= position;
        textView.setText(request.getTeg());
        Button button=(Button) view.findViewById(R.id.agree);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), Stream.class);
                inflater.getContext().startActivity(intent);
            }
        });
        button = (Button) view.findViewById(R.id.unsubscribe);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                list.remove(i);
                notifyDataSetChanged();
            }
        });


        return view;
    }
    private Request getRequest(int position)
    {
        return (Request) getItem(position);
    }
}

