package com.appsnipp.loginsamples.Navigation_Profile.ui.resource;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.list.adapter;
import com.appsnipp.loginsamples.list.data;

import java.util.ArrayList;

public class ResourceFragment extends Fragment {

    ListView ll;
    adapter ad;

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
        ll=(ListView) root.findViewById(R.id.list_res);
        ArrayList<data> itemlist= new ArrayList<>();
        data d[]={new data("b-101","club","kausahl","kausahl jethava",R.drawable.club),new data("b-102","club","kaushik","kausahik mokariya",R.drawable.club)
                ,new data("b-102","club","priyanshu","priyanshu desai",R.drawable.club)
                ,new data("b-102","club","aditya","aditya panchal",R.drawable.club)
                ,new data("b-102","club","shakshi","shakshi patel",R.drawable.club)
                ,new data("b-102","club","danika","danika bhatt",R.drawable.club)
                ,new data("b-102","club","kuldeep","kuldeep jethava",R.drawable.club)};
        for(int i=0;i<d.length;i++)

        {
            itemlist.add(d[i]);

        }
       ad= new adapter(getActivity().getApplicationContext(),R.layout.resource_list,itemlist);
        ll.setAdapter(ad);


        return root;
    }
}