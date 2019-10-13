package com.appsnipp.loginsamples.registration;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.appsnipp.loginsamples.Navigation_Profile.Navigation_Activity;
import com.appsnipp.loginsamples.R;

public class photoupload extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listforphotoupload);
    }

    public void camera(View view) {
        Toast.makeText(this, "gallery", Toast.LENGTH_SHORT).show();
    }

    public void gallery(View view) {
        Toast.makeText(this, "gallery", Toast.LENGTH_SHORT).show();
    }
}
