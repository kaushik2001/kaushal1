package com.appsnipp.loginsamples.bill_recycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.responce.bill_child_responce;
import com.appsnipp.loginsamples.apiinterface.responce.spnt_total_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.bill_child_get_set;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.bill_get_set;
import com.appsnipp.loginsamples.event_recycle_view.event_adapter;
import com.appsnipp.loginsamples.event_recycle_view.event_data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bill_adapter extends RecyclerView.Adapter<bill_adapter.ViewHolder>{

    List<bill_child_get_set> li;

    List<bill_get_set> bill_data;

    Context context;

    public bill_adapter(List<bill_get_set> bill_data, Context context) {
        this.bill_data = bill_data;
        this.context = context;
    }


    @NonNull
    @Override
    public bill_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bill_layout,null);

        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull bill_adapter.ViewHolder viewHolder, int i) {

        bill_get_set d=bill_data.get(i);

        viewHolder.bi_no.setText(""+d.getId());
        viewHolder.bi_name.setText(d.getBillname());
        viewHolder.bi_total.setText(d.getTotalamt());
        //viewHolder.bi_due.setText();
        viewHolder.bi_date.setText(d.getBilldate());

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        viewHolder.childRV.setLayoutManager(layoutManager);
        viewHolder.childRV.setHasFixedSize(true);


        String r=""+bill_data.get(i).getId();
        String r2=""+bill_data.get(i).getTotalamt();

       loadspent(r,viewHolder);
      spntdue(r,r2,viewHolder);




        // List<bill_child_data> bill_ch_li;
     //   bill_ch_li = new ArrayList<>();
//        if(bill_data.get(i).getB_no()=="000001") {
//            bill_child_data data[] = {new bill_child_data("Light","1000")
//                    ,new bill_child_data( "Decoration","50000")};
//            for (int j = 0; j < data.length; j++) {
//                bill_ch_li.add(data[j]);
//            }
//        }
//        else if (bill_data.get(i).getB_no()=="000002"){
//            bill_child_data data[] = {new bill_child_data("Light","1000")
//                    ,new bill_child_data( "Decoration","50000")
//                    ,new bill_child_data( "DJ","50000")
//                    ,new bill_child_data( "Tax","50000")};
//            for (int j = 0; j < data.length; j++) {
//                bill_ch_li.add(data[j]);
//            }
//        }
//        else if (bill_data.get(i).getB_no()=="000003"){
//            bill_child_data data[] = {new bill_child_data( "DJ","50000")
//                    ,new bill_child_data( "Decoration","5040")};
//            for (int j = 0; j < data.length; j++) {
//                bill_ch_li.add(data[j]);
//            }
//        }
//        else if (bill_data.get(i).getB_no()=="000004"){
//            bill_child_data data[] = {new bill_child_data("Tax","1000")
//                    ,new bill_child_data( "Decoration","50000")};
//            for (int j = 0; j < data.length; j++) {
//                bill_ch_li.add(data[j]);
//            }
//        }
//        else if (bill_data.get(i).getB_no()=="000005"){
//            bill_child_data data[] = {new bill_child_data("Light","1000")
//                    ,new bill_child_data( "LightBill","50000")};
//            for (int j = 0; j < data.length; j++) {
//                bill_ch_li.add(data[j]);
//            }
//        }
//        else if (bill_data.get(i).getB_no()=="000006"){
//            bill_child_data data[] = {new bill_child_data("Light","1000")
//                    ,new bill_child_data( "Tax","50000")
//                    ,new bill_child_data( "DJ","50000")
//                    ,new bill_child_data( "Decoration","50000")};
//            for (int j = 0; j < data.length; j++) {
//                bill_ch_li.add(data[j]);
//            }
//        }
//        bill_child_adapter bca=new bill_child_adapter(bill_ch_li);
//        viewHolder.childRV.setAdapter(bca);
//        bca.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return bill_data.size();
    }



    private void spntdue(String r, String r2,ViewHolder viewHolder) {
        Api api= ApiClient.getClient().create(Api.class);
        Call<spnt_total_responce> call=api.spnttotal("spenttotal",r);
        call.enqueue(new Callback<spnt_total_responce>() {
            @Override
            public void onResponse(Call<spnt_total_responce> call, Response<spnt_total_responce> response) {
                if (response.body().getSuccess()==200) {
                    String o = response.body().getDe().getSum();
                    if(o!=null) {
                        int pp = Integer.parseInt(o);
                        int i2 = Integer.parseInt(r2);
                        int re = i2 - pp;
                        viewHolder.bi_due.setText("" + re);
                    }
                    else {
                        viewHolder.bi_due.setText(r2);
                    }

                }
            }

            @Override
            public void onFailure(Call<spnt_total_responce> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public RecyclerView childRV;
        public TextView bi_no,bi_name,bi_total,bi_due,bi_date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bi_no=(TextView) itemView.findViewById(R.id.bill_text2);
            bi_name=(TextView) itemView.findViewById(R.id.bill_name2);
            bi_total=(TextView) itemView.findViewById(R.id.bill_total);
            bi_due=(TextView) itemView.findViewById(R.id.bill_due);
            bi_date=(TextView) itemView.findViewById(R.id.bill_date2);
            childRV=(RecyclerView) itemView.findViewById(R.id.bill_child_recycle);
        }
    }

    public void loadspent(String s,ViewHolder viewHolder)
    {
        Api api= ApiClient.getClient().create(Api.class);
        Call<bill_child_responce> call=api.billchild("billspntdetails",s);
        call.enqueue(new Callback<bill_child_responce>() {
            @Override
            public void onResponse(Call<bill_child_responce> call, Response<bill_child_responce> response) {
                if (response.body().getSuccess()==200) {

                    li=response.body().getDe();
                    Collections.reverse(li);
                    bill_child_adapter ada=new bill_child_adapter(li,context);

                    viewHolder.childRV.setAdapter(ada);
                    ada.notifyDataSetChanged();
//                    LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_anmimation_fall_down);
//                    recyclerView.setLayoutAnimation(layoutAnimationController);
                }
                else {
                    Toast.makeText(context, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }



            }

            @Override
            public void onFailure(Call<bill_child_responce> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

