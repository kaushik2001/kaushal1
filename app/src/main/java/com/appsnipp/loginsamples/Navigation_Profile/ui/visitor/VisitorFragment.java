package com.appsnipp.loginsamples.Navigation_Profile.ui.visitor;

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

public class VisitorFragment extends Fragment {

    private VisitorViewModel visitorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        visitorViewModel =
                ViewModelProviders.of(this).get(VisitorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_visitor, container, false);
        final TextView textView = root.findViewById(R.id.text_visitor);
        visitorViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}