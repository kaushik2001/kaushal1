package com.appsnipp.loginsamples.Navigation_Profile.ui.noticeboard;

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

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.notice_recycle.notice_adapter;
import com.appsnipp.loginsamples.notice_recycle.notice_data;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoardFragment extends Fragment {
    RecyclerView recyclerView;
    List<notice_data> li;
    private NoticeBoardViewModel noticeBoardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        noticeBoardViewModel =
                ViewModelProviders.of(this).get(NoticeBoardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notice, container, false);
//        final TextView textView = root.findViewById(R.id.text_notice);
        noticeBoardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //              textView.setText(s);
            }
        });
        li=new ArrayList<>();
        recyclerView=(RecyclerView) root.findViewById(R.id.noticerecycle);
        notice_data data2[]={new notice_data("Last Notice for Maintenance Due","Defaulters post Oct 12th will be levied a final of 10%","K.L.Mokariya","14 min.ago,11:20 AM")
                ,new notice_data("Lift Maintenance","Inform that lifts are under maintanance on 10/2/2019 from 3 to 5 pm kindly cooperat.","P.D.Desai","14 min.ago,11:20 AM")
                ,new notice_data("Upcoming Road Maintenance","Walkway before block A will be closed down for annual maintenance.","K.J.Jethva","14 min.ago,11:20 AM")
                ,new notice_data("Upcoming Road Maintenance","Walkway before block A will be closed down for annual maintenance.","K.J.Jethva","14 min.ago,11:20 AM")
                ,new notice_data("Upcoming Road Maintenance","Walkway before block A will be closed down for annual maintenance.","K.J.Jethva","14 min.ago,11:20 AM")};


            for(int i=0;i< data2.length;i++){
                li.add(data2[i]);
            }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notice_adapter ada=new notice_adapter(li);
        recyclerView.setAdapter(ada);



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