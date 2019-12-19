package com.appsnipp.loginsamples.notice_recycle;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;

import java.util.List;

public class notice_adapter extends RecyclerView.Adapter<notice_adapter.ViewHolder> {
    public notice_adapter(List<notice_data> notice_data) {
        this.notice_data = notice_data;
    }

    private List<notice_data> notice_data;

    @NonNull
    @Override
    public notice_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notice_layout,null);
        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull notice_adapter.ViewHolder holder, int i) {
        notice_data d= notice_data.get(i);
        holder.n_head.setText(d.getHeading());
        holder.n_desc.setText(d.getDesc());
        holder.n_name.setText(d.getName());
        holder.n_time.setText(d.getTime());


    }

    @Override
    public int getItemCount() {
        return notice_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
    public TextView n_head,n_desc,n_name,n_time;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            n_head=(TextView) itemView.findViewById(R.id.notice_head);
            n_desc=(TextView) itemView.findViewById(R.id.notice_desc);
            n_name=(TextView) itemView.findViewById(R.id.notice_add_name);
            n_time=(TextView) itemView.findViewById(R.id.notice_time);
        }
    }
}

