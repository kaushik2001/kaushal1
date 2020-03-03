package com.appsnipp.loginsamples.document_recycle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.document_get_set;

import java.util.List;

public class document_adapter extends RecyclerView.Adapter<document_adapter.ViewHolder> {
    private List<document_get_set> documnet_data;
    Context mcontext;
    public document_adapter(List<document_get_set> documnetData,Context mcontext){
        this.documnet_data= documnetData;
        this.mcontext=mcontext;
    }

    @NonNull
    @Override
    public document_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.document_layout,null);

       ViewHolder viewHolder=new ViewHolder(itemview);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mcontext, document_view.class);
                i.putExtra("dec_name",documnet_data.get(viewHolder.getAdapterPosition()).getDocument_file());
                mcontext.startActivity(i);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull document_adapter.ViewHolder viewHolder, int i) {
       document_get_set d=documnet_data.get(i);

        viewHolder.dc_name.setText(d.getDocument_name());
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
