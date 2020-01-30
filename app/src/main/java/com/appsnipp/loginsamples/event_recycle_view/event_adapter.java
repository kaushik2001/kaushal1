package com.appsnipp.loginsamples.event_recycle_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.event_get_set;

import java.util.List;

public class event_adapter extends RecyclerView.Adapter<event_adapter.ViewHolder> {

    Context mcontext;

    private List<event_get_set> event_data;
    public event_adapter(Context mcontext, List<event_get_set> event_data){
        this.event_data=event_data;
        this.mcontext=mcontext;
    }

    @NonNull
    @Override
    public event_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_layout,null);

        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull event_adapter.ViewHolder viewHolder, int i) {
        event_get_set d=event_data.get(i);

        viewHolder.ev_name.setText(d.getName());
        viewHolder.ev_place.setText(d.getPlace());
        viewHolder.ev_member.setText(d.getMembers());
        viewHolder.ev_date.setText(d.getDate());
        viewHolder.ev_time.setText(d.getTime());
    }

    @Override
    public int getItemCount() {
        return event_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView ev_name,ev_place,ev_member,ev_date,ev_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ev_name=(TextView) itemView.findViewById(R.id.event_name2);
            ev_place=(TextView) itemView.findViewById(R.id.event_place2);
            ev_member=(TextView) itemView.findViewById(R.id.event_member2);
            ev_date=(TextView) itemView.findViewById(R.id.event_date2);
            ev_time=(TextView) itemView.findViewById(R.id.event_Time2);

        }
    }
}
