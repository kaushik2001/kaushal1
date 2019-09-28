package com.appsnipp.loginsamples.Navigation_Profile.ui.buildingdetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsnipp.loginsamples.R;

public class BuildingDetailsFragment extends Fragment {

    private BuildingDetailViewModel buildingDetailViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buildingDetailViewModel =
                ViewModelProviders.of(this).get(BuildingDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buildingdetails, container, false);
       // final TextView textView = root.findViewById(R.id.text_building);
        buildingDetailViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        return root;
    }
}