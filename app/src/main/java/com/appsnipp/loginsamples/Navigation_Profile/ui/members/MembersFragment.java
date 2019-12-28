package com.appsnipp.loginsamples.Navigation_Profile.ui.members;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Button;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.event_recycle_view.event_adapter;
import com.appsnipp.loginsamples.event_recycle_view.event_data;
import com.appsnipp.loginsamples.member_recycle.member_adapter;
import com.appsnipp.loginsamples.member_recycle.member_data;

import java.util.ArrayList;
import java.util.List;

public class MembersFragment extends Fragment {

    RecyclerView recyclerView;
    List<member_data> lia;
    Button b1,b2,b3,b4,b5,b6;
    private MembersViewModel membersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        membersViewModel =
                ViewModelProviders.of(this).get(MembersViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_member, container, false);
       b1=(Button) root.findViewById(R.id.mb_1);
        b2=(Button) root.findViewById(R.id.mb_2);
        b3=(Button) root.findViewById(R.id.mb_3);
        b4=(Button) root.findViewById(R.id.mb_4);
        b5=(Button) root.findViewById(R.id.mb_5);
        b6=(Button) root.findViewById(R.id.mb_6);


        recyclerView=(RecyclerView) root.findViewById(R.id.member_recycle);
        lia=new ArrayList<>();
        final member_data dataa[]={new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
                ,new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
                ,new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
                ,new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
                ,new member_data("a-301","kaushal","7383846827","kaushal12@gmail.com","4 member")};
        final member_data datab[]={new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("b-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")};
        final member_data datac[]={new member_data("c-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("c-304","kaushik","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("c-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("c-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("c-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")};
        final member_data datad[]={new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
                ,new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
                ,new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
                ,new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")
                ,new member_data("d-301","kaushal","7383846827","kaushal12@gmail.com","4 member")};
        final member_data datae[]={new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("e-303","priyanshu","1111111111","kdhduahu@gmail.com","5 member")};
        final member_data dataf[]={new member_data("f-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("f-304","kaushik","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("f-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("f-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")
                ,new member_data("f-303","kaushik","1111111111","kdhduahu@gmail.com","5 member")};

        for(int i=0;i< dataa.length;i++) {
            lia.add(dataa[i]);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setBackgroundResource(R.drawable.member_button_white);
                b2.setBackgroundResource(R.drawable.member_button);
                b3.setBackgroundResource(R.drawable.member_button);
                b4.setBackgroundResource(R.drawable.member_button);
                b5.setBackgroundResource(R.drawable.member_button);
                b6.setBackgroundResource(R.drawable.member_button);

                lia.clear();
                for(int i=0;i< dataa.length;i++){


                    lia.add(dataa[i]);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    member_adapter ev=new member_adapter(lia);
                    recyclerView.setAdapter(ev);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                b2.setBackgroundResource(R.drawable.member_button_white);
                b5.setBackgroundResource(R.drawable.member_button);
                b3.setBackgroundResource(R.drawable.member_button);
                b4.setBackgroundResource(R.drawable.member_button);
                b1.setBackgroundResource(R.drawable.member_button);
                b6.setBackgroundResource(R.drawable.member_button);
                lia.clear();
                for(int i=0;i< datab.length;i++){

                    lia.add(datab[i]);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    member_adapter ev=new member_adapter(lia);
                    recyclerView.setAdapter(ev);

                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b3.setBackgroundResource(R.drawable.member_button_white);
                b2.setBackgroundResource(R.drawable.member_button);
                b5.setBackgroundResource(R.drawable.member_button);
                b4.setBackgroundResource(R.drawable.member_button);
                b1.setBackgroundResource(R.drawable.member_button);
                b6.setBackgroundResource(R.drawable.member_button);
                lia.clear();
                for(int i=0;i< datac.length;i++){

                    lia.add(datac[i]);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    member_adapter ev=new member_adapter(lia);
                    recyclerView.setAdapter(ev);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b4.setBackgroundResource(R.drawable.member_button_white);
                b2.setBackgroundResource(R.drawable.member_button);
                b3.setBackgroundResource(R.drawable.member_button);
                b5.setBackgroundResource(R.drawable.member_button);
                b1.setBackgroundResource(R.drawable.member_button);
                b6.setBackgroundResource(R.drawable.member_button);
                lia.clear();
                for(int i=0;i< datad.length;i++){

                    lia.add(datad[i]);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    member_adapter ev=new member_adapter(lia);
                    recyclerView.setAdapter(ev);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b5.setBackgroundResource(R.drawable.member_button_white);
                b2.setBackgroundResource(R.drawable.member_button);
                b3.setBackgroundResource(R.drawable.member_button);
                b4.setBackgroundResource(R.drawable.member_button);
                b1.setBackgroundResource(R.drawable.member_button);
                b6.setBackgroundResource(R.drawable.member_button);
                lia.clear();
                for(int i=0;i< datae.length;i++){

                    lia.add(datae[i]);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    member_adapter ev=new member_adapter(lia);
                    recyclerView.setAdapter(ev);
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b6.setBackgroundResource(R.drawable.member_button_white);
                b2.setBackgroundResource(R.drawable.member_button);
                b3.setBackgroundResource(R.drawable.member_button);
                b4.setBackgroundResource(R.drawable.member_button);
                b1.setBackgroundResource(R.drawable.member_button);
                b5.setBackgroundResource(R.drawable.member_button);
                lia.clear();
                for(int i=0;i< dataf.length;i++){

                    lia.add(dataf[i]);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    member_adapter ev=new member_adapter(lia);
                    recyclerView.setAdapter(ev);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        member_adapter ev=new member_adapter(lia);
        recyclerView.setAdapter(ev);
        return root;
    }





}