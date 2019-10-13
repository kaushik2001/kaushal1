package com.appsnipp.loginsamples.registration;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsnipp.loginsamples.LoginActivity;
import com.appsnipp.loginsamples.R;

public class Registration extends AppCompatActivity {
    EditText fn,ln,mn,ma;
    AlertDialog.Builder builder;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        fn=(EditText) findViewById(R.id.firstname);
        ln=(EditText) findViewById(R.id.lastname);
        mn=(EditText) findViewById(R.id.mobileno);
        ma=(EditText) findViewById(R.id.mail);



    }

    public void pass(View view) {
        boolean v=true;
        String s1=fn.getText().toString();
        String s2=ln.getText().toString();
        String s3=mn.getText().toString();
        String s4=ma.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String MobilePattern = "[0-9]{10}";

        if (s1.isEmpty()){
            fn.setError("Enter Firstname");
            v=false;
        }
        if (s2.isEmpty()){
            ln.setError("Enter Lastname");
            v=false;
        }
        if (s3.isEmpty() || !s3.matches(MobilePattern)){

            mn.setError("Enter Mobile Number");
            v=false;
        }
        if (s4.isEmpty() || !s4.matches(emailPattern)){
            ma.setError("Enter Email");
            v=false;
        }
        if (v == true) {

            Intent i = new Intent(Registration.this, password.class);
            startActivity(i);
        }


    }

    public void sigin(View view) {
        super.onBackPressed();
        finish();
    }

    public void loginback(View view) {
       super.onBackPressed();
        finish();
    }


    public void photoupload(View view) {
        builder= new AlertDialog.Builder(this);
        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.listforphotoupload,null);
        builder.setView(v);

        builder.setCancelable(true);
        AlertDialog alert=builder.create();
        alert.show();
    }

    public void gallery(View view) {
        Toast.makeText(this, "gallery", Toast.LENGTH_SHORT).show();
    }

    public void camera(View view) {
        Toast.makeText(this, "camera", Toast.LENGTH_SHORT).show();
    }
}