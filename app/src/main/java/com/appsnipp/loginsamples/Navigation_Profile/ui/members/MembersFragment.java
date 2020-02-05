package com.appsnipp.loginsamples.Navigation_Profile.ui.members;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;

import com.appsnipp.loginsamples.apiinterface.responce.member_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.member_get_set;

import com.appsnipp.loginsamples.member_recycle.member_adapter;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MembersFragment extends Fragment {

    RecyclerView recyclerView;
    List<member_get_set> lia;
    Button b1, b2, b3, b4, b5, b6;
    member_adapter me;
    private MembersViewModel membersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        membersViewModel =
                ViewModelProviders.of(this).get(MembersViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_member, container, false);
        b1 = (Button) root.findViewById(R.id.mb_1);
        b2 = (Button) root.findViewById(R.id.mb_2);
        b3 = (Button) root.findViewById(R.id.mb_3);
        b4 = (Button) root.findViewById(R.id.mb_4);
        b5 = (Button) root.findViewById(R.id.mb_5);
        b6 = (Button) root.findViewById(R.id.mb_6);


        recyclerView = (RecyclerView) root.findViewById(R.id.member_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        lia=new ArrayList<>();
//        final member_data dataa[]={new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
//                ,new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
//                ,new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
//                ,new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
//                ,new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")};
//        final member_data datab[]={new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")};
//        final member_data datac[]={new member_data("c-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("c-304","kaushik","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("c-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("c-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("c-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")};
//        final member_data datad[]={new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
//                ,new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
//                ,new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
//                ,new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
//                ,new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")};
//        final member_data datae[]={new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")};
//        final member_data dataf[]={new member_data("f-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("f-304","kaushik","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("f-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("f-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
//                ,new member_data("f-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")};
//
//        for(int i=0;i< dataa.length;i++) {
//            lia.add(dataa[i]);
//        }

        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                b1.setBackgroundResource(R.drawable.dotfill);
                b2.setBackgroundResource(R.drawable.dot);
                b3.setBackgroundResource(R.drawable.dot);
                b4.setBackgroundResource(R.drawable.dot);
                b5.setBackgroundResource(R.drawable.dot);
                b6.setBackgroundResource(R.drawable.dot);
                b1.setTextColor(getResources().getColor(R.color.whiteTextColor));
                b2.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b3.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b4.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b5.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b6.setTextColor(getResources().getColor(R.color.primaryTextColor));


               // lia.clear();

                loadmember("a");


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                b2.setBackgroundResource(R.drawable.dotfill);
                b1.setBackgroundResource(R.drawable.dot);
                b3.setBackgroundResource(R.drawable.dot);
                b4.setBackgroundResource(R.drawable.dot);
                b5.setBackgroundResource(R.drawable.dot);
                b6.setBackgroundResource(R.drawable.dot);

                b2.setTextColor(getResources().getColor(R.color.whiteTextColor));
                b1.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b3.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b4.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b5.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b6.setTextColor(getResources().getColor(R.color.primaryTextColor));


               // lia.clear();
                loadmember("b");


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                b3.setBackgroundResource(R.drawable.dotfill);
                b2.setBackgroundResource(R.drawable.dot);
                b1.setBackgroundResource(R.drawable.dot);
                b4.setBackgroundResource(R.drawable.dot);
                b5.setBackgroundResource(R.drawable.dot);
                b6.setBackgroundResource(R.drawable.dot);

                b3.setTextColor(getResources().getColor(R.color.whiteTextColor));
                b2.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b1.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b4.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b5.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b6.setTextColor(getResources().getColor(R.color.primaryTextColor));

                //lia.clear();
                loadmember("c");

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                b4.setBackgroundResource(R.drawable.dotfill);
                b2.setBackgroundResource(R.drawable.dot);
                b3.setBackgroundResource(R.drawable.dot);
                b1.setBackgroundResource(R.drawable.dot);
                b5.setBackgroundResource(R.drawable.dot);
                b6.setBackgroundResource(R.drawable.dot);

                b4.setTextColor(getResources().getColor(R.color.whiteTextColor));
                b2.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b3.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b1.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b5.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b6.setTextColor(getResources().getColor(R.color.primaryTextColor));

              //  lia.clear();
                loadmember("d");

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                b5.setBackgroundResource(R.drawable.dotfill);
                b2.setBackgroundResource(R.drawable.dot);
                b3.setBackgroundResource(R.drawable.dot);
                b4.setBackgroundResource(R.drawable.dot);
                b1.setBackgroundResource(R.drawable.dot);
                b6.setBackgroundResource(R.drawable.dot);

                b5.setTextColor(getResources().getColor(R.color.whiteTextColor));
                b2.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b3.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b4.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b1.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b6.setTextColor(getResources().getColor(R.color.primaryTextColor));
             //   lia.clear();
                loadmember("e");

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                b6.setBackgroundResource(R.drawable.dotfill);
                b2.setBackgroundResource(R.drawable.dot);
                b3.setBackgroundResource(R.drawable.dot);
                b4.setBackgroundResource(R.drawable.dot);
                b5.setBackgroundResource(R.drawable.dot);
                b1.setBackgroundResource(R.drawable.dot);


                b6.setTextColor(getResources().getColor(R.color.whiteTextColor));
                b2.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b3.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b4.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b5.setTextColor(getResources().getColor(R.color.primaryTextColor));
                b1.setTextColor(getResources().getColor(R.color.primaryTextColor));
              //  lia.clear();


                loadmember("f");


            }
        });
        loadmember("a");
        return root;
    }
    public void loadmember(String s) {


        Api api = ApiClient.getClient().create(Api.class);
        Call<member_responce> call = api.membersdetail("membersdetail", s);
call.enqueue(new Callback<member_responce>() {
    @Override
    public void onResponse(Call<member_responce> call, Response<member_responce> response) {

        if (response.body().getSuccess() == 200) {
            lia = response.body().getDe();
            Collections.reverse(lia);
            me = new member_adapter(getContext(), lia);
            recyclerView.setAdapter(me);
            LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation_from_right);
            recyclerView.setLayoutAnimation(layoutAnimationController);

        } else {
            Toast.makeText(getContext(), response.body().getMessage() + "", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<member_responce> call, Throwable t) {
        Toast.makeText(getContext(), t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

    }
});


    }



}