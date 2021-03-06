package com.appsnipp.loginsamples.notice_recycle;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appsnipp.loginsamples.Navigation_Profile.ui.complain.ComplainFragment;
import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.notice_get_set;

import java.util.List;

public class notice_adapter extends RecyclerView.Adapter<notice_adapter.ViewHolder> {
    Dialog mydialog;
    Context mcontext;
    private List<notice_get_set> notice_data;
    public notice_adapter(Context mcontext,List<notice_get_set> notice_data) {
        this.notice_data = notice_data;
        this.mcontext=mcontext;
    }


    @NonNull
    @Override
    public notice_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notice_layout,null);
        ViewHolder viewHolder=new ViewHolder(itemview);
        mydialog =new Dialog(mcontext);
        mydialog.setContentView(R.layout.popup_layout_notice);

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView di_nt_head=(TextView)mydialog.findViewById(R.id.notice_head_dialog);
                TextView di_nt_desc=(TextView)mydialog.findViewById(R.id.notice_desc_dialog);
                TextView di_nt_time=(TextView)mydialog.findViewById(R.id.notice_time_dialog);
                TextView di_nt_add_name=(TextView)mydialog.findViewById(R.id.notice_add_name_dialog);
                di_nt_head.setText(notice_data.get(viewHolder.getAdapterPosition()).getHeader());
                di_nt_desc.setText(notice_data.get(viewHolder.getAdapterPosition()).getDescription());
                di_nt_time.setText(notice_data.get(viewHolder.getAdapterPosition()).getDatetime());
                di_nt_add_name.setText(notice_data.get(viewHolder.getAdapterPosition()).getAname());
                mydialog.show();
                return false;
            }
        });
        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (mydialog.isShowing()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    int action = motionEvent.getActionMasked();
                    if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        mydialog.dismiss();
                        return true;
                    }
                }
                return false;

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull notice_adapter.ViewHolder holder, int i) {
        notice_get_set d= notice_data.get(i);
        holder.n_head.setText(d.getHeader());
        holder.n_desc.setText(d.getDescription());
        holder.n_name.setText(d.getAname());
        holder.n_time.setText(d.getDatetime());


    }

    @Override
    public int getItemCount() {
        return notice_data.size();
        //return notice_data == null ? 0 : notice_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView n_head,n_desc,n_name,n_time;

        RelativeLayout li;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            li=(RelativeLayout) itemView.findViewById(R.id.notice_layout_id) ;
            n_head=(TextView) itemView.findViewById(R.id.notice_head);
            n_desc=(TextView) itemView.findViewById(R.id.notice_desc);
            n_name=(TextView) itemView.findViewById(R.id.notice_add_name);
            n_time=(TextView) itemView.findViewById(R.id.notice_time);
        }
    }
}

