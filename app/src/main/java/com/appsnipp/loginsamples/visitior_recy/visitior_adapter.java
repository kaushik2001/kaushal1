package com.appsnipp.loginsamples.visitior_recy;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.TransitionRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.visi_de;

import java.util.List;

public class visitior_adapter extends RecyclerView.Adapter<visitior_adapter.ViewHolder> {

    Dialog mydialog;
    Context mcontext;
    private List<visi_de> list;
    public visitior_adapter(Context mcontext,List<visi_de> list){
        this.list=list;
        this.mcontext=mcontext;
    }

    @NonNull
    @Override
    public visitior_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.visitior_layout,null);
        ViewHolder viewHolder=new ViewHolder(itemview);
        mydialog =new Dialog(mcontext);
        mydialog.setContentView(R.layout.pop_layout_visit_details);
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                EditText di_exit=(EditText) mydialog.findViewById(R.id.pop_exit);
                Button b=(Button)mydialog.findViewById(R.id.pop_ok);
                String s=di_exit.getText().toString();
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewHolder.ve_exit.setText(di_exit.getText().toString());
                        mydialog.dismiss();
                    }
                });
                mydialog.show();
                return false;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull visitior_adapter.ViewHolder viewHolder, int i) {
        visi_de l=list.get(i);
        viewHolder.ve_name.setText(l.getFullname());
        viewHolder.ve_mobile.setText(l.getMobno());
        viewHolder.ve_date.setText(l.getDate());
        viewHolder.ve_entery.setText(l.getTime());
        viewHolder.ve_exit.setText(l.getExittime());
        viewHolder.ve_flat.setText(l.getHouseno());
        viewHolder.ve_ve_no.setText(l.getNumofvisi());
        viewHolder.ve_vehi_no.setText(l.getVno());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView ve_name,ve_mobile,ve_date,ve_entery,ve_exit,ve_flat,ve_ve_no,ve_vehi_no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ve_name=(TextView) itemView.findViewById(R.id.visitior_name);
            ve_mobile=(TextView) itemView.findViewById(R.id.visitior_mobile);
            ve_date=(TextView) itemView.findViewById(R.id.visitior_date);
            ve_entery=(TextView) itemView.findViewById(R.id.visitior_entry);
            ve_exit=(TextView) itemView.findViewById(R.id.visitior_exit);
            ve_flat=(TextView) itemView.findViewById(R.id.visitior_flatno);
            ve_ve_no=(TextView) itemView.findViewById(R.id.visitior_no_visi_entry);
            ve_vehi_no=(TextView) itemView.findViewById(R.id.veno_entry);

        }
    }
}
