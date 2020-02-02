package com.appsnipp.loginsamples.Navigation_Profile.ui.visitor;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.responce.visidetail_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.visi_de;
import com.appsnipp.loginsamples.visitior_recy.visitior_adapter;
import com.appsnipp.loginsamples.visitior_recy.visitior_data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorFragment extends Fragment {
    RecyclerView recyclerView;
    List<visi_de> li;
    TextView t;
    visitior_adapter vi;
    private VisitorViewModel visitorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        visitorViewModel =
                ViewModelProviders.of(this).get(VisitorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_visitor, container, false);

        recyclerView=(RecyclerView) root.findViewById(R.id.visitior_recycle);
//        li=new ArrayList<>();
//        visitior_data data[]={new visitior_data("jethava kaushal","7383846827","21/04/2020","1:20pm","2:20pm","B-102")
//                ,new visitior_data("mokariya kaushik","7383846827","21/04/2020","1:40pm","2:40pm","B-102")
//                ,new visitior_data("desai priyanshu","7383846827","21/04/2020","2:20pm","3:50pm","C-103")
//                ,new visitior_data("bhatt danika","7383846827","20/04/2020","1:20pm","2:20pm","B-108")
//                ,new visitior_data("patel shakshi","7383846827","20/04/2020","4:20pm","9:20pm","B-109")};
//        for(int i=0;i< data.length;i++){
//            li.add(data[i]);
//        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Api api= ApiClient.getClient().create(Api.class);
        Call<visidetail_responce> call= api.visidetail("gatekvisidetail");
        call.enqueue(new Callback<visidetail_responce>() {
            @Override
            public void onResponse(Call<visidetail_responce> call, Response<visidetail_responce> response) {
                li=response.body().getDe();
                Collections.reverse(li);
                vi=new visitior_adapter(getContext(),li);
                recyclerView.setAdapter(vi);
            }

            @Override
            public void onFailure(Call<visidetail_responce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });



        return root;
    }
    private void runLayoutanimation (RecyclerView recyclerView)
    {
        Context context=recyclerView.getContext();
        LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anmimation_fall_down);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}