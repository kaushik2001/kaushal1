package com.appsnipp.loginsamples.document_recycle;

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

public class document_adapter extends RecyclerView.Adapter<document_adapter.ViewHolder> {
    private List<documnet_data> documnet_data;
    public document_adapter(List<documnet_data> documnetData){
        this.documnet_data= documnetData;
    }

    @NonNull
    @Override
    public document_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.document_layout,null);

       ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull document_adapter.ViewHolder viewHolder, int i) {
       documnet_data d=documnet_data.get(i);

        viewHolder.dc_name.setText(d.pname);
    }

    @Override
    public int getItemCount() {
        return documnet_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView dc_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dc_name=(TextView) itemView.findViewById(R.id.pdf_name);


        }
    }
}
