package com.appsnipp.loginsamples.cmp_recycle;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.cmp_get_set;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class cmp_adapter extends RecyclerView.Adapter<cmp_adapter.ViewHolder> {
    Context mcontext;
    Dialog mydialog;
    private List<cmp_get_set> cmp_data;

    public cmp_adapter(Context mcontext, List<cmp_get_set> cmp_data) {
        this.cmp_data = cmp_data;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complain_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemview);
        mydialog = new Dialog(mcontext);
        mydialog.setContentView(R.layout.popup_layout_complain);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView ctitle = (TextView) mydialog.findViewById(R.id.cmp_title);
                TextView title = (TextView) mydialog.findViewById(R.id.cm_title);
                TextView description = (TextView) mydialog.findViewById(R.id.cm_descr);
                TextView flat = (TextView) mydialog.findViewById(R.id.cm_flat);
                //TextView sts = (TextView) mydialog.findViewById(R.id.cm_status);
                ImageView img = (ImageView) mydialog.findViewById(R.id.cm_img);
                title.setText(cmp_data.get(viewHolder.getAdapterPosition()).getCtitle());
                description.setText(cmp_data.get(viewHolder.getAdapterPosition()).getDiscription());
                flat.setText(cmp_data.get(viewHolder.getAdapterPosition()).getFlatno());
                //sts.setText(cmp_data.get(viewHolder.getAdapterPosition()).getStatus());
                String sts1=cmp_data.get(viewHolder.getAdapterPosition()).getStatus();
                if (sts1.equals("Pending")){
                    ctitle.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.erroricon1,0);
                }else {
                    ctitle.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.succ,0);
                }

                Glide.with(mcontext)
                        .load(cmp_data.get(viewHolder.getAdapterPosition()).getDocument_file())
                        .into(img);


                mydialog.show();
            }
        });



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        cmp_get_set d=cmp_data.get(i);

        viewHolder.cp_title.setText(d.getCtitle());
        viewHolder.cp_des.setText(d.getDiscription());
        viewHolder.cp_flat.setText(d.getFlatno());
        viewHolder.cp_sts.setText(d.getStatus());
      //  viewHolder.cp_img.setText(d.getDate());
        Glide.with(mcontext)
                .load(d.getDocument_file())
                .into(viewHolder.cp_img);

    }

    @Override
    public int getItemCount() {
        return cmp_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView cp_title,cp_des,cp_flat,cp_sts;
        public ImageView cp_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cp_title=(TextView) itemView.findViewById(R.id.cmpr_ttl);
            cp_des=(TextView) itemView.findViewById(R.id.cmpr_desc);
            cp_flat=(TextView) itemView.findViewById(R.id.cmpr_flatno);
            cp_sts=(TextView) itemView.findViewById(R.id.cmpr_status);
            cp_img=(ImageView) itemView.findViewById(R.id.cmpr_img);


        }
    }

}
