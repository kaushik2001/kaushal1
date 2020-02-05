package com.appsnipp.loginsamples.member_recycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.responce.member_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.member_get_set;

import java.util.List;

public class member_adapter extends RecyclerView.Adapter<member_adapter.ViewHolder> {
    Context mcontext;

    private List<member_get_set> member_data;
    public member_adapter(Context mcontext,List<member_get_set> member_data){
        this.member_data=member_data;
        this.mcontext=mcontext;
    }


    @NonNull
    @Override
    public member_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.member_details_layout,null);

        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull member_adapter.ViewHolder viewHolder, int i) {
      member_get_set d=member_data.get(i);

        viewHolder.me_flat.setText(d.getHouseno());
        viewHolder.me_name.setText(d.getFname());
        viewHolder.me_mobile.setText(d.getMobno());
        viewHolder.me_email.setText(d.getEmail());
    }

    @Override
    public int getItemCount() {
        return member_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView me_flat,me_name,me_mobile,me_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            me_flat=(TextView) itemView.findViewById(R.id.member_flatno);
            me_name=(TextView) itemView.findViewById(R.id.member_name2);
            me_mobile=(TextView) itemView.findViewById(R.id.member_mobile2);
            me_email=(TextView) itemView.findViewById(R.id.member_email2);


        }
    }
}
