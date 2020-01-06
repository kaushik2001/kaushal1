package com.appsnipp.loginsamples.resource_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.maintence_recycle.maintence_adapter;
import com.appsnipp.loginsamples.maintence_recycle.maintence_data;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private List<data> data;
    public adapter(List<data> data){
        this.data=data;
    }


    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resource_list,null);

        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder viewHolder, int i) {
        data d=data.get(i);

        viewHolder.re_name.setText(d.text1);
        viewHolder.flat_no.setText(d.text);
        viewHolder.re_name_.setText(d.text2);
        viewHolder.re_detail.setText(d.text3);
        viewHolder.re_img.setImageResource(d.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView re_name,flat_no,re_name_,re_detail;
        ImageView re_img ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            re_name=(TextView) itemView.findViewById(R.id.res_name);
            flat_no=(TextView) itemView.findViewById(R.id.flat_no);
            re_name_=(TextView) itemView.findViewById(R.id.name);
            re_detail=(TextView) itemView.findViewById(R.id.details);
            re_img=(ImageView) itemView.findViewById(R.id.res_img);


        }
    }
}
