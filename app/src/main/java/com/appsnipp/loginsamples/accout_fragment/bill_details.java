package com.appsnipp.loginsamples.accout_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.bill_recycle.bill_adapter;
import com.appsnipp.loginsamples.bill_recycle.bill_data;
import com.appsnipp.loginsamples.event_recycle_view.event_adapter;
import com.appsnipp.loginsamples.event_recycle_view.event_data;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class bill_details extends Fragment {
    RecyclerView recyclerView;
    List<bill_data> li;


    public bill_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill_details, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.bill_recycle);
        li = new ArrayList<>();
        bill_data data[] = {new bill_data("000001","diwali","12000","1200")
                ,new bill_data("000002","holi","12345","234")
                ,new bill_data( "000003","society paint","2345356","00")
                ,new bill_data("000004","diwali","12000","1200")
                ,new bill_data("000005","holi","12345","234")
                ,new bill_data( "000006","society paint","2345356","00")};
        for(int i=0;i< data.length;i++){
            li.add(data[i]);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bill_adapter ev=new bill_adapter(li,getContext());
        recyclerView.setAdapter(ev);

        return view;
    }

}
