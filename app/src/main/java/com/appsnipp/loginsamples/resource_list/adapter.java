package com.appsnipp.loginsamples.resource_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;

import java.util.ArrayList;

public class adapter extends ArrayAdapter<data> {
    ArrayList<data> items;
    private int layid;
    public adapter(Context context, int resource,ArrayList<data> items) {
        super(context, resource,items);
        this.layid = resource;
        this.items = items;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public data getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        data d=items.get(position);
        ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder=new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layid, parent, false);

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.res_img);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.res_name);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.flat_no);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.name);
            viewHolder.textView3 = (TextView) convertView.findViewById(R.id.details);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.textView1.setText(d.getText());
        viewHolder.textView.setText(d.getText1());
        viewHolder.textView2.setText(d.getText2());
        viewHolder.textView3.setText(d.getText3());
        viewHolder.imageView.setImageResource(d.getImg());

        return convertView;
    }
    public static class ViewHolder{
        TextView textView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        ImageView imageView;
    }
}
