package com.it_lab.teleport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alex on 13.07.15.
 */
public class DataAdapter extends BaseAdapter {
    private List<Data> list;
    private LayoutInflater inflater;

    public DataAdapter(Context context,List<Data> list) {
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
        textView.setText(getData(position).getTeg());

        return view;
    }
    private Data getData(int position)
    {
        return (Data) getItem(position);
    }
}
