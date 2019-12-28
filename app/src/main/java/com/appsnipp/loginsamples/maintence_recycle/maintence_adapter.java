package com.appsnipp.loginsamples.maintence_recycle;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.bill_recycle.bill_adapter;
import com.appsnipp.loginsamples.bill_recycle.bill_data;

import java.util.List;

public class maintence_adapter extends RecyclerView.Adapter<maintence_adapter.ViewHolder> {

    private List<maintence_data> maintence_data;
    public maintence_adapter(List<maintence_data> maintence_data){
        this.maintence_data=maintence_data;
    }


    @NonNull
    @Override
    public maintence_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.maintence_layout,null);

        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull maintence_adapter.ViewHolder viewHolder, int i) {
       maintence_data d=maintence_data.get(i);

        viewHolder.ma_name.setText(d.m_name);
        viewHolder.ma_ammount.setText(d.m_ammount);
        viewHolder.ma_last_date.setText(d.m_lastdate);

    }

    @Override
    public int getItemCount() {
        return maintence_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView ma_name,ma_ammount,ma_last_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ma_name=(TextView) itemView.findViewById(R.id.maintence_name2);
            ma_ammount=(TextView) itemView.findViewById(R.id.maintence_ammount2);
            ma_last_date=(TextView) itemView.findViewById(R.id.maintence_last_date2);


        }
    }
}
