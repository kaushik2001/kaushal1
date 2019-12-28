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
import com.appsnipp.loginsamples.maintence_recycle.maintence_adapter;
import com.appsnipp.loginsamples.maintence_recycle.maintence_data;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class maintence_details extends Fragment {

    RecyclerView recyclerView;
    List<maintence_data> li;
    public maintence_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maintence_deails, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.maintence_recycle);
        li = new ArrayList<>();
        maintence_data data[]={new maintence_data("society paint","23000","09-12-2019")
        ,new maintence_data("diwali","23476","18-02-2020"),
        new maintence_data("holi","12450","12-05-2020")
        ,new maintence_data("society paint","23000","09-12-2019")
                ,new maintence_data("diwali","23476","18-02-2020"),
                new maintence_data("holi","12450","12-05-2020")};
        for(int i=0;i< data.length;i++){
           li.add(data[i]);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        maintence_adapter ma=new maintence_adapter(li);
        recyclerView.setAdapter(ma);
        return view;
    }

}
