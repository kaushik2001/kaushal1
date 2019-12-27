package com.appsnipp.loginsamples.accout_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsnipp.loginsamples.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class bill_details extends Fragment {


    public bill_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_details, container, false);
    }

}
