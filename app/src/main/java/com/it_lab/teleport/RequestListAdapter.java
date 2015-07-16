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
 * Created by alex on 13.07.15.
 */
public class RequestListAdapter extends BaseAdapter {
    private List<Request> list;
    private LayoutInflater inflater;

    public RequestListAdapter(Context context, List<Request> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view ==null)
        {
            view=inflater.inflate(R.layout.item,parent,false);
        }

        TextView textView= (TextView) view.findViewById(R.id.textView);
        final Request request = getRequest(position);
        textView.setText(request.getTeg());
        Button button=(Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(inflater.getContext(),VideoPlayActivity.class);
                intent.putExtra("TAG", request.getTeg());
                intent.putExtra("URI", request.getUri());
                inflater.getContext().startActivity(intent);
            }
        });
        return view;
    }
    private Request getRequest(int position)
    {
        return (Request) getItem(position);
    }
}
