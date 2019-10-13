package com.appsnipp.loginsamples.Navigation_Profile.ui.noticeboard;

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

public class NoticeBoardFragment extends Fragment {

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
        return root;
    }
}