package com.appsnipp.loginsamples.member_recycle;

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

public class member_adapter extends RecyclerView.Adapter<member_adapter.ViewHolder> {

    private List<member_data> member_data;
    public member_adapter(List<member_data> member_data){
        this.member_data=member_data;
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
      member_data d=member_data.get(i);

        viewHolder.me_flat.setText(d.m_flat);
        viewHolder.me_name.setText(d.m_name);
        viewHolder.me_mobile.setText(d.m_mobile);
        viewHolder.me_email.setText(d.m_email);
        viewHolder.me_member.setText(d.m_member);
    }

    @Override
    public int getItemCount() {
        return member_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView me_flat,me_name,me_mobile,me_email,me_member;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            me_flat=(TextView) itemView.findViewById(R.id.member_flatno);
            me_name=(TextView) itemView.findViewById(R.id.member_name2);
            me_mobile=(TextView) itemView.findViewById(R.id.member_mobile2);
            me_email=(TextView) itemView.findViewById(R.id.member_email2);
            me_member=(TextView) itemView.findViewById(R.id.member_total2);

        }
    }
}
