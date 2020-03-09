package com.appsnipp.loginsamples.accout_fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.responce.mainte_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.mainte_get_set;
import com.appsnipp.loginsamples.bill_recycle.bill_adapter;
import com.appsnipp.loginsamples.bill_recycle.bill_data;
import com.appsnipp.loginsamples.maintence_recycle.maintence_adapter;
import com.appsnipp.loginsamples.maintence_recycle.maintence_data;
import com.appsnipp.loginsamples.storage.sareprefrencelogin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class maintence_details extends Fragment {

    RecyclerView recyclerView;
    List<mainte_get_set> li;
    maintence_adapter ada;
    SwipeRefreshLayout swipe;
    public maintence_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maintence_deails, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.maintence_recycle);
        swipe=(SwipeRefreshLayout) view.findViewById(R.id.swipe_mainte);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.lite_blue));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        loadmainte();
                        swipe.setRefreshing(false);

                    }
                }, 2000);
                //swipe.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadmainte();
        return view;
    }
    public void loadmainte()
    {
        User user= sareprefrencelogin.getInstance(getContext()).getuser();
        String s=user.getHouseno();
        Api api= ApiClient.getClient().create(Api.class);
        Call<mainte_responce> call=api.main_details("mainjdetails",s);
        call.enqueue(new Callback<mainte_responce>() {
            @Override
            public void onResponse(Call<mainte_responce> call, Response<mainte_responce> response) {
                if (response.body().getSuccess()==200) {

                    li=response.body().getDe();
                    Collections.reverse(li);
                    ada=new maintence_adapter(li,getContext());
                    recyclerView.setAdapter(ada);
                    LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_anmimation_fall_down);
                    recyclerView.setLayoutAnimation(layoutAnimationController);
                }
                else {
                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }



            }

            @Override
            public void onFailure(Call<mainte_responce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
