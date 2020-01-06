package com.appsnipp.loginsamples.bill_recycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.event_recycle_view.event_adapter;
import com.appsnipp.loginsamples.event_recycle_view.event_data;

import java.util.ArrayList;
import java.util.List;

public class bill_adapter extends RecyclerView.Adapter<bill_adapter.ViewHolder>{

    List<bill_data> bill_data;

    Context context;

    public bill_adapter(List<bill_data> bill_data, Context context) {
        this.bill_data = bill_data;
        this.context = context;
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

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        viewHolder.childRV.setLayoutManager(layoutManager);
        viewHolder.childRV.setHasFixedSize(true);



        List<bill_child_data> bill_ch_li;
        bill_ch_li = new ArrayList<>();
        if(bill_data.get(i).getB_no()=="000001") {
            bill_child_data data[] = {new bill_child_data("Light","1000")
                    ,new bill_child_data( "Decoration","50000")};
            for (int j = 0; j < data.length; j++) {
                bill_ch_li.add(data[j]);
            }
        }
        else if (bill_data.get(i).getB_no()=="000002"){
            bill_child_data data[] = {new bill_child_data("Light","1000")
                    ,new bill_child_data( "Decoration","50000")
                    ,new bill_child_data( "DJ","50000")
                    ,new bill_child_data( "Tax","50000")};
            for (int j = 0; j < data.length; j++) {
                bill_ch_li.add(data[j]);
            }
        }
        else if (bill_data.get(i).getB_no()=="000003"){
            bill_child_data data[] = {new bill_child_data( "DJ","50000")
                    ,new bill_child_data( "Decoration","5040")};
            for (int j = 0; j < data.length; j++) {
                bill_ch_li.add(data[j]);
            }
        }
        else if (bill_data.get(i).getB_no()=="000004"){
            bill_child_data data[] = {new bill_child_data("Tax","1000")
                    ,new bill_child_data( "Decoration","50000")};
            for (int j = 0; j < data.length; j++) {
                bill_ch_li.add(data[j]);
            }
        }
        else if (bill_data.get(i).getB_no()=="000005"){
            bill_child_data data[] = {new bill_child_data("Light","1000")
                    ,new bill_child_data( "LightBill","50000")};
            for (int j = 0; j < data.length; j++) {
                bill_ch_li.add(data[j]);
            }
        }
        else if (bill_data.get(i).getB_no()=="000006"){
            bill_child_data data[] = {new bill_child_data("Light","1000")
                    ,new bill_child_data( "Tax","50000")
                    ,new bill_child_data( "DJ","50000")
                    ,new bill_child_data( "Decoration","50000")};
            for (int j = 0; j < data.length; j++) {
                bill_ch_li.add(data[j]);
            }
        }
        bill_child_adapter bca=new bill_child_adapter(bill_ch_li);
        viewHolder.childRV.setAdapter(bca);
        bca.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return bill_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public RecyclerView childRV;
        public TextView bi_no,bi_name,bi_total,bi_due;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bi_no=(TextView) itemView.findViewById(R.id.bill_text2);
            bi_name=(TextView) itemView.findViewById(R.id.bill_name2);
            bi_total=(TextView) itemView.findViewById(R.id.bill_total);
            bi_due=(TextView) itemView.findViewById(R.id.bill_due);
            childRV=(RecyclerView) itemView.findViewById(R.id.bill_child_recycle);


        }
    }
}

