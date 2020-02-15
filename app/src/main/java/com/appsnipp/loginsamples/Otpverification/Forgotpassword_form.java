package com.appsnipp.loginsamples.Otpverification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.appsnipp.loginsamples.LoginActivity;
import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.CommanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Forgotpassword_form extends AppCompatActivity {
    EditText mono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpasword_activity);
        mono = (EditText) findViewById(R.id.forgot_mobile_ed);
        mono.requestFocus();

        findViewById(R.id.forget_mob_sendotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = mono.getText().toString().trim();

                if (mobile.isEmpty() || mobile.length() < 10) {
                    mono.setError("Enter a valid mobile");
                    mono.requestFocus();
                    return;
                }

                Api api = ApiClient.getClient().create(Api.class);
                Call<CommanResponse> call = api.mobnoex("passmobnoex", mobile);
                call.enqueue(new Callback<CommanResponse>() {
                    @Override
                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                        if (response.body().getSuccess()==200) {
                            Intent intent = new Intent(Forgotpassword_form.this, Forgetpassword_otp.class);
                            intent.putExtra("mobile", mobile);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Forgotpassword_form.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<CommanResponse> call, Throwable t) {
                        Toast.makeText(Forgotpassword_form.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });
//        mcallback= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            @Override
//            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//
//            }
//
//            @Override
//            public void onVerificationFailed(FirebaseException e) {
//
//            }
//
//            @Override
//            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);
//                veri_code=s;
//                Toast.makeText(Forgotpassword_form.this, "code Sent", Toast.LENGTH_SHORT).show();
//            }
//
//        };
//    }
//    public  void send_sms(View v)
//    {   String m=mono.getText().toString().trim();
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                m,60, TimeUnit.SECONDS,this,mcallback
//
//        );
//    }
//
//    public void changepa(PhoneAuthCredential credential)
//    {
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful())
//                        {
//                            Toast.makeText(Forgotpassword_form.this, "otp match", Toast.LENGTH_SHORT).show();
//                            Intent i = new Intent(Forgotpassword_form.this, password.class);
//                            startActivity(i);
//                        }
//                    }
//                });
    }


    public void loginback2(View view) {
        Intent i = new Intent(Forgotpassword_form.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

//    public void password(View view) {
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

//        String code=e1.getText().toString().trim();
//        if(code.length()!=0)
//        {
//            veri_phone(veri_code,code);
//        }
//    }

//    private void veri_phone(String veri_code, String code) {
//        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(veri_code,code);
//        changepa(credential);
//    }

    public void regiback(View view) {
        Intent i = new Intent(Forgotpassword_form.this, LoginActivity.class);
        startActivity(i);
        finish();
    }


//
//    @Override
//    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void afterTextChanged(Editable editable) {
//
//        if (editable.length() == 1) {
//            if (e1.length() == 1) {
//                e2.requestFocus();
//            }
//
//            if (e2.length() == 1) {
//                e3.requestFocus();
//            }
//            if (e3.length() == 1) {
//                e4.requestFocus();
//            }
////        } else if (editable.length() == 0) {
////            if (e4.length() == 0) {
////                e3.requestFocus();
////            }
////            if (e3.length() == 0) {
////                e2.requestFocus();
////            }
////            if (e2.length() == 0) {
////                e1.requestFocus();
////            }
////
////
//        }
//
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(mono.length()==0) {
//            mono.requestFocus();
//        }
//
//        if (e4.length() == 0) {
//            if (keyCode == event.KEYCODE_DEL) {
//                e3.requestFocus();
//            }
//        }
//        if (e3.length() == 0) {
//            if (keyCode == event.KEYCODE_DEL) {
//                e2.requestFocus();
//            }
//        }
//        if (e2.length() == 0) {
//            if (keyCode == event.KEYCODE_DEL) {
//                e1.requestFocus();
//            }
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
}

