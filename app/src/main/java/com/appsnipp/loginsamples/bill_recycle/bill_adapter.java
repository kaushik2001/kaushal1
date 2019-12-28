package com.appsnipp.loginsamples.bill_recycle;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.event_recycle_view.event_adapter;
import com.appsnipp.loginsamples.event_recycle_view.event_data;

import java.util.List;

public class bill_adapter extends RecyclerView.Adapter<bill_adapter.ViewHolder>{

private List<bill_data> bill_data;
public bill_adapter(List<bill_data> bill_data){
        this.bill_data=bill_data;
        }

    @NonNull
    @Override
    public bill_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bill_layout,null);

        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull bill_adapter.ViewHolder viewHolder, int i) {
       bill_data d=bill_data.get(i);

        viewHolder.bi_no.setText(d.b_no);
        viewHolder.bi_name.setText(d.b_name);
        viewHolder.bi_total.setText(d.b_total);
        viewHolder.bi_due.setText(d.b_due);

    }

    @Override
    public int getItemCount() {
        return bill_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView bi_no,bi_name,bi_total,bi_due;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bi_no=(TextView) itemView.findViewById(R.id.bill_text2);
            bi_name=(TextView) itemView.findViewById(R.id.bill_name2);
            bi_total=(TextView) itemView.findViewById(R.id.bill_total);
            bi_due=(TextView) itemView.findViewById(R.id.bill_due);


        }
    }
}

