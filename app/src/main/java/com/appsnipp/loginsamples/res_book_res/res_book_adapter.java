package com.appsnipp.loginsamples.res_book_res;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.res_book_get_set;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.resource_get_set;
import com.appsnipp.loginsamples.resource_list.resource_adapter;

import java.util.List;

public class res_book_adapter extends RecyclerView.Adapter<res_book_adapter.ViewHolder> {
    Context mcontext;
    private List<res_book_get_set> data;
    public res_book_adapter(Context mcontext,List<res_book_get_set> data){
        this.data=data;
        this.mcontext=mcontext;
    }


    @NonNull
    @Override
    public res_book_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_res_layout,null);

       ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull res_book_adapter.ViewHolder viewHolder, int i) {
        res_book_get_set d=data.get(i);

        viewHolder.res_name.setText(d.getRes_name());
        viewHolder.rdate.setText(d.getDate());
        viewHolder.rtime.setText(d.getTime());
        viewHolder.rbookname.setText(d.getBookname());
        // viewHolder.re_img.setImageResource(d.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView res_name,rdate,rtime,rbookname;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            res_name=(TextView) itemView.findViewById(R.id.bres_name);
            rdate=(TextView) itemView.findViewById(R.id.bres_date);
            rtime=(TextView) itemView.findViewById(R.id.bres_time);
            rbookname=(TextView) itemView.findViewById(R.id.bres_bookname);



        }
    }
}
