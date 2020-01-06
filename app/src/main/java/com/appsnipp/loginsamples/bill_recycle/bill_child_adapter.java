package com.appsnipp.loginsamples.bill_recycle;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;

import java.util.List;

public class bill_child_adapter extends RecyclerView.Adapter<bill_child_adapter.MyviewHolder> {

    private List<bill_child_data> bill_child_data;

    public bill_child_adapter(List<bill_child_data> bill_child_data) {
        this.bill_child_data = bill_child_data;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bill_child_layout,null);

        MyviewHolder myviewHolder=new MyviewHolder(itemview);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
        bill_child_data d=bill_child_data.get(i);

        myviewHolder.tname.setText(d.tdname);
        myviewHolder.tamt.setText(d.tdamt);

    }

    @Override
    public int getItemCount() {
        return bill_child_data.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        TextView tname,tamt;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            tname=(TextView) itemView.findViewById(R.id.bill_child_tname);
            tamt=(TextView) itemView.findViewById(R.id.bill_child_tamt);

        }
    }
}
