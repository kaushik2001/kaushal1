package com.appsnipp.loginsamples.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.LoginActivity;
import com.appsnipp.loginsamples.R;

public class password extends AppCompatActivity {
    EditText password,conformpassword;
//    TextView tosttext1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_password_activity);
password=(EditText) findViewById(R.id.pass1);
conformpassword=(EditText) findViewById(R.id.cpass);
//        tosttext1=(TextView) findViewById(R.id.toasttext);
    }

    public void regiback(View view) {
        super.onBackPressed();

    }

    public void checkpassmatchp(View view) {
        String s1=password.getText().toString();
        String s2=conformpassword.getText().toString();
        boolean k=true;
        if(s1.isEmpty()){
            password.setError("Invailid Password");
            k=false;
        }
        if(s2.isEmpty()){
            conformpassword.setError("Invailid Conform-Password");
            k=false;
        }
        if(!s2.equals(s1)){
            conformpassword.setError("Not Match");
            k=false;
        }
        if(k==true){
//            tosttext1.setText("match passeord");
            LayoutInflater li = getLayoutInflater();
            View layout = li.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast));
            Toast t=new Toast(getApplicationContext());
            t.setDuration(Toast.LENGTH_SHORT);
            t.setView(layout);
            t.show();
        }
    }
}
