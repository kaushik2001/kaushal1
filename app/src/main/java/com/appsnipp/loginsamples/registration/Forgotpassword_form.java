package com.appsnipp.loginsamples.registration;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.appsnipp.loginsamples.LoginActivity;
import com.appsnipp.loginsamples.R;

public class Forgotpassword_form extends AppCompatActivity implements TextWatcher {
EditText e1,e2,e3,e4,mono;
KeyEvent k;
//TextView tosttext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpasword_activity);
        e1= findViewById(R.id.editTextone);
        e2= findViewById(R.id.editText_two);
        e3= findViewById(R.id.editTex_three);
        e4= findViewById(R.id.editTex_four);
        mono=(EditText) findViewById(R.id.forgotmobile);
        e1.addTextChangedListener(this);
        e2.addTextChangedListener(this);
        e3.addTextChangedListener(this);
        e4.addTextChangedListener(this);
//       tosttext1=(TextView) findViewById(R.id.toasttext);
        mono.requestFocus();
    }

    public void loginback2(View view) {
        Intent i=new Intent(Forgotpassword_form.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void password(View view) {
        boolean v=true;
        String s3=mono.getText().toString();
        String MobilePattern = "[0-9]{10}";
        if (s3.isEmpty() || !s3.matches(MobilePattern)){

            mono.setError("Invailid");
            v=false;
        }
        if(v==true) {
            Intent i = new Intent(Forgotpassword_form.this, password.class);
            startActivity(i);
        }
        else {
//           tosttext1.setText("match passeord");
            LayoutInflater li = getLayoutInflater();
            View layout = li.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast));
            Toast t=new Toast(getApplicationContext());
            t.setDuration(Toast.LENGTH_SHORT);
            t.setView(layout);
            t.show();
        }
    }

    public void regiback(View view) {
        Intent i=new Intent(Forgotpassword_form.this,LoginActivity.class);
        startActivity(i);
        finish();
    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (editable.length() == 1) {
            if (e1.length() == 1) {
                e2.requestFocus();
            }

            if (e2.length() == 1) {
                e3.requestFocus();
            }
            if (e3.length() == 1) {
                e4.requestFocus();
            }
//        } else if (editable.length() == 0) {
//            if (e4.length() == 0) {
//                e3.requestFocus();
//            }
//            if (e3.length() == 0) {
//                e2.requestFocus();
//            }
//            if (e2.length() == 0) {
//                e1.requestFocus();
//            }
//
//
      }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mono.length()==0) {
            mono.requestFocus();
        }

            if (e4.length() == 0) {
                if (keyCode == event.KEYCODE_DEL) {
                    e3.requestFocus();
                }
            }
            if (e3.length() == 0) {
                if (keyCode == event.KEYCODE_DEL) {
                    e2.requestFocus();
                }
            }
            if (e2.length() == 0) {
                if (keyCode == event.KEYCODE_DEL) {
                    e1.requestFocus();
                }
            }

        return super.onKeyDown(keyCode, event);
    }
}
