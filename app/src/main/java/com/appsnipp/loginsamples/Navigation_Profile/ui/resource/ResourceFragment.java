package com.appsnipp.loginsamples.Navigation_Profile.ui.resource;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.responce.resource_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.resource_get_set;
import com.appsnipp.loginsamples.resource_list.resource_adapter;
import com.appsnipp.loginsamples.resource_list.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourceFragment extends Fragment {


    RecyclerView recyclerView;
    resource_adapter ev;
    List<resource_get_set> li;
    SwipeRefreshLayout swipe;
    AlertDialog.Builder builder;
    EditText resf_name,resf_detail,resf_capacity,resf_charge;
    Button Sv;
    String resfs_name,resfs_detail,resfs_capacity,resfs_charge;
    private ResourceViewModel resourceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        resourceViewModel =
                ViewModelProviders.of(this).get(ResourceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_resources, container, false);
        //final TextView textView = root.findViewById(R.id.text_resource);
        resourceViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //     textView.setText(s);
            }
        });
        recyclerView=(RecyclerView) root.findViewById(R.id.list_res);
        swipe=(SwipeRefreshLayout) root.findViewById(R.id.swipe_resource);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.lite_blue));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        loadresource();
                        swipe.setRefreshing(false);
                        LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation_slide_from_bottom);
                        recyclerView.setLayoutAnimation(layoutAnimationController);

                    }
                }, 2000);
               // swipe.setRefreshing(false);
            }
        });
//        li=new ArrayList<>();
//        data azad[]={new data("b-101","club","kausahl","kausahl jethava",R.drawable.club),new data("b-102","club","kaushik","kausahik mokariya",R.drawable.club)
//                ,new data("b-102","club","priyanshu","priyanshu desai",R.drawable.club)
//                ,new data("b-102","club","aditya","aditya panchal",R.drawable.club)
//                ,new data("b-102","club","shakshi","shakshi patel",R.drawable.club)
//                ,new data("b-102","club","danika","danika bhatt",R.drawable.club)
//                ,new data("b-102","club","kuldeep","kuldeep jethava",R.drawable.club)};
//
//        for(int i=0;i<azad.length;i++)
//
//        {
//            li.add(azad[i]);
//
//        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadresource();


        return root;
    }
    public void loadresource()
    {
        Api api= ApiClient.getClient().create(Api.class);
        Call<resource_responce> call= api.resourcedetail("resourcedetail");
        call.enqueue(new Callback<resource_responce>() {
            @Override
            public void onResponse(Call<resource_responce> call, Response<resource_responce> response) {
                if (response.body().getSuccess()==200) {
                    li=response.body().getDe();
                    Collections.reverse(li);
                    ev=new resource_adapter(getContext(),li);
                    recyclerView.setAdapter(ev);
                }
                else {
                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<resource_responce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }
}