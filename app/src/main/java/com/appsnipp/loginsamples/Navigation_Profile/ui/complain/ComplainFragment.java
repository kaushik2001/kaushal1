package com.appsnipp.loginsamples.Navigation_Profile.ui.complain;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.Navigation_Profile.Navigation_Activity;
import com.appsnipp.loginsamples.R;

public class ComplainFragment extends Fragment {
    Button complainphoto;
    TextView gallery, camera;
    AlertDialog.Builder builder;
    private ComplainViewModel complainViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        complainViewModel =
                ViewModelProviders.of(this).get(ComplainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_complain, container, false);

        complainphoto = root.findViewById(R.id.cmpphoto);


        complainphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.fragementcamera, null);
                gallery = v.findViewById(R.id.ga);
                camera = v.findViewById(R.id.ca);
                builder = new AlertDialog.Builder(getActivity());

                builder.setView(v);

                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "gallery", Toast.LENGTH_SHORT).show();
                    }
                });
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "camera", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setCancelable(true);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });


        return root;
    }

}