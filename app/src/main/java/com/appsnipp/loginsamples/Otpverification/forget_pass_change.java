package com.appsnipp.loginsamples.Otpverification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class forget_pass_change extends AppCompatActivity {
    EditText password, conformpassword;
//    TextView tosttext1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_change);
        password = (EditText) findViewById(R.id.pass1);
        conformpassword = (EditText) findViewById(R.id.cpass);
//        tosttext1=(TextView) findViewById(R.id.toasttext);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(forget_pass_change.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    public void regiback(View view) {
        //super.onBackPressed();
        Intent intent = new Intent(forget_pass_change.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    public void checkpassmatchp(View view) {
        String s1 = password.getText().toString();
        String s2 = conformpassword.getText().toString();
        boolean k = true;
        if (s1.isEmpty() || s1.length() < 8) {
            password.setError(" 8 character necessary");
            k = false;
        }
        if (s2.isEmpty()) {
            conformpassword.setError("Invailid Conform-Password");
            k = false;
        }
        if (!s2.equals(s1)) {
            conformpassword.setError("Not Match");
            k = false;
        }
        if (k == true) {
//            tosttext1.setText("match passeord");
//            LayoutInflater li = getLayoutInflater();
//            View layout = li.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast));
//            Toast t=new Toast(getApplicationContext());
//            t.setDuration(Toast.LENGTH_SHORT);
//
//            t.setView(layout);
//            t.show();
            Intent i1 = getIntent();
            String mob = i1.getStringExtra("mob");
            Api api = ApiClient.getClient().create(Api.class);
            Call<CommanResponse> call = api.changepass("changepass", mob, s1);
            call.enqueue(new Callback<CommanResponse>() {
                @Override
                public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                    if (response.body().getSuccess()==200) {
                        Toast.makeText(forget_pass_change.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(forget_pass_change.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(forget_pass_change.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<CommanResponse> call, Throwable t) {
                    Toast.makeText(forget_pass_change.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

                }
            });


        }
    }
}