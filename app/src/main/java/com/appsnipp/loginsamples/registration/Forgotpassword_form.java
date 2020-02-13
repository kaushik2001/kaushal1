package com.appsnipp.loginsamples.registration;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.appsnipp.loginsamples.LoginActivity;
import com.appsnipp.loginsamples.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Forgotpassword_form extends AppCompatActivity implements TextWatcher {
    EditText e1,e2,e3,e4,e5,e6,mono;
    KeyEvent k;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
    String veri_code;
//TextView tosttext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpasword_activity);
        e1= findViewById(R.id.editTextone);
        e2= findViewById(R.id.editText_two);
        e3= findViewById(R.id.editTex_three);
        e4= findViewById(R.id.editTex_four);
        e5= findViewById(R.id.editTex_five);
        e6= findViewById(R.id.editTex_six);
        mono=(EditText) findViewById(R.id.forgotmobile);
        e1.addTextChangedListener(this);
        e2.addTextChangedListener(this);
        e3.addTextChangedListener(this);
        e4.addTextChangedListener(this);
        e5.addTextChangedListener(this);
        e6.addTextChangedListener(this);
//       tosttext1=(TextView) findViewById(R.id.toasttext);
        mono.requestFocus();
        auth=FirebaseAuth.getInstance();

        mcallback= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                veri_code=s;
                Toast.makeText(Forgotpassword_form.this, "code Sent", Toast.LENGTH_SHORT).show();
            }

        };
    }
    public  void send_sms(View v)
    {   String m=mono.getText().toString().trim();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                m,60, TimeUnit.SECONDS,this,mcallback

        );
    }

    public void changepa(PhoneAuthCredential credential)
    {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Forgotpassword_form.this, "otp match", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Forgotpassword_form.this, forget_pass_change.class);
                            i.putExtra("mob",mono.getText().toString().trim());
                            startActivity(i);
                        }
                    }
                });
    }

    public void loginback2(View view) {
        Intent i=new Intent(Forgotpassword_form.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void password(View view) {
//        boolean v=true;
//        String s3=mono.getText().toString();
//        String MobilePattern = "[0-9]{10}";
//        if (s3.isEmpty() || !s3.matches(MobilePattern)){
//
//            mono.setError("Invailid");
//            v=false;
//        }
//        if(v==true) {
//            Intent i = new Intent(Forgotpassword_form.this, password.class);
//            startActivity(i);
//        }
//        else {
////           tosttext1.setText("match passeord");
//            LayoutInflater li = getLayoutInflater();
//            View layout = li.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast));
//            Toast t=new Toast(getApplicationContext());
//            t.setDuration(Toast.LENGTH_SHORT);
//            t.setView(layout);
//            t.show();
//        }

        String code=""+e1.getText()+e2.getText()+e3.getText()+e4.getText()+e5.getText()+e6.getText();
        if(code.length()!=0)
        {
            veri_phone(veri_code,code);
        }
    }

    private void veri_phone(String veri_code, String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(veri_code,code);
        changepa(credential);
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
            if (e4.length() == 1) {
                e5.requestFocus();
            }
            if (e5.length() == 1) {
                e6.requestFocus();
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
        if (e6.length() == 0) {
            if (keyCode == event.KEYCODE_DEL) {
                e5.requestFocus();
            }
        }
        if (e5.length() == 0) {
            if (keyCode == event.KEYCODE_DEL) {
                e4.requestFocus();
            }
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
