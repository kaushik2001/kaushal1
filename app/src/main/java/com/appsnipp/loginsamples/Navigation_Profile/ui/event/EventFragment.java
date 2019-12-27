package com.appsnipp.loginsamples.Navigation_Profile.ui.event;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.event_recycle_view.event_adapter;
import com.appsnipp.loginsamples.event_recycle_view.event_data;
import com.appsnipp.loginsamples.visitior_recy.visitior_adapter;
import com.appsnipp.loginsamples.visitior_recy.visitior_data;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {
    RecyclerView recyclerView;
    List<event_data> li;
    private EventViewModel eventViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventViewModel =
                ViewModelProviders.of(this).get(EventViewModel.class);
        View root = inflater.inflate(R.layout.fragment_event, container, false);
        recyclerView=(RecyclerView) root.findViewById(R.id.event_recycle);
        li=new ArrayList<>();
        event_data data[] ={new event_data("diwali","party plot","5 member","23/04/2019","2:45 pm")
        ,new event_data("holi","play ground","6 member","23/06/2019","7:40 am")
        ,new event_data("new year","club house","6 member","28/04/2019","7:90 am")
        ,new event_data("navratri","play ground","All Family","22/09/2019","4:40 am")
                ,new event_data("new year","club house","6 member","28/04/2019","7:90 am")
                ,new event_data("holi","play ground","6 member","23/06/2019","7:40 am")
        };
        for(int i=0;i< data.length;i++){
            li.add(data[i]);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        event_adapter ev=new event_adapter(li);
        recyclerView.setAdapter(ev);
        return root;
    }
}