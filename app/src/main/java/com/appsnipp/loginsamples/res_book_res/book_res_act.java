package com.appsnipp.loginsamples.res_book_res;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.CommanResponse;
import com.appsnipp.loginsamples.apiinterface.responce.res_book_responce;
import com.appsnipp.loginsamples.apiinterface.responce.resource_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.res_book_get_set;
import com.appsnipp.loginsamples.resource_list.resource_adapter;
import com.appsnipp.loginsamples.storage.sareprefrencelogin;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class book_res_act extends AppCompatActivity {
    RecyclerView recyclerView;
    res_book_adapter ev;
    AlertDialog.Builder builder;
    String res_namee;
    List<res_book_get_set> li;
    SwipeRefreshLayout swipe;
    EditText res_time,res_date;
    Button sv;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_res_act);
        Intent i=getIntent();
        res_namee=i.getStringExtra("res_name");
        t=(TextView) findViewById(R.id.nm);
        t.setText(res_namee);
        recyclerView=(RecyclerView) findViewById(R.id.res_book_recycle);
        swipe=(SwipeRefreshLayout) findViewById(R.id.swipe_res_book);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.lite_blue));
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        loadresourcebook();
                        swipe.setRefreshing(false);


                    }
                }, 2000);
                // swipe.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadresourcebook();


//        findViewById(R.id.book_btn_res).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //alert.dismiss();
////                alert.show();
//            }
//        });


    }

    public void loadresourcebook()
    {
        Api api= ApiClient.getClient().create(Api.class);
        Call<res_book_responce> call= api.booklist("resourcedetail",res_namee);
        call.enqueue(new Callback<res_book_responce>() {
            @Override
            public void onResponse(Call<res_book_responce> call, Response<res_book_responce> response) {
                if (response.body().getSuccess()==200) {
                    li=response.body().getDe();
                    Collections.reverse(li);
                    ev=new res_book_adapter(getApplicationContext(),li);
                    recyclerView.setAdapter(ev);
                    LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_animation_slide_from_bottom);
                    recyclerView.setLayoutAnimation(layoutAnimationController);
                }
                else {
                    Toast.makeText(getApplicationContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<res_book_responce> call, Throwable t) {
                Toast.makeText(getApplicationContext() , t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

            }
        });




    }

    public void booksavebtn(View view) {
        builder= new AlertDialog.Builder(this);
        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.book_res_submit_layout,null);
        builder.setView(v);
        builder.setCancelable(true);
        AlertDialog alert=builder.create();


        res_date=(EditText)v.findViewById(R.id.res_book_btn_date);
        res_time=(EditText)v.findViewById(R.id.res_book_btn_time);


//        res_date.setText(new SimpleDateFormat("DD-MM-yyyy", Locale.US).format(new Date()));
//        res_time.setText(new SimpleDateFormat("hh:mm a", Locale.US).format(new Date()));
        res_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){


                    final Calendar c = Calendar.getInstance();



                    // Get Current Time

                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(book_res_act.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

//                                    if (hourOfDay>13){
//
//                                        e.setText((hourOfDay-12) + ":" + minute+" PM");
//                                    }
//                                    else {
//                                        if(hourOfDay==0){
//                                            hourOfDay=12;
//                                        }
//                                        e.setText(hourOfDay + ":" + minute+" AM");
//
//                                    }

                                    boolean isPM = (hourOfDay >= 12);
                                    res_time.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();





                }
            }
        });
        res_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){



                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);




                    DatePickerDialog datePickerDialog = new DatePickerDialog(book_res_act.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    res_date.setText(String.format("%02d-%02d-%04d",dayOfMonth,monthOfYear+1,year));
                                    //e1.setText(dayOfMonth+"-"+(monthOfYear + 1)+"-"+year);

                                }

                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();




                }
            }
        });









        sv=(Button) v.findViewById(R.id.res_book_btn_book);
        sv.findViewById(R.id.res_book_btn_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String book_date=res_date.getText().toString().trim();
                String book_time=res_time.getText().toString().trim();
                User user= sareprefrencelogin.getInstance(getApplication()).getuser();
                String book_name=user.getFname()+" "+user.getLname();
                //res_namee for res_name


                Api api= ApiClient.getClient().create(Api.class);
                Call<CommanResponse> call= api.book_res("resourceregi",res_namee,book_date,book_time,book_name);
                call.enqueue(new Callback<CommanResponse>() {
                    @Override
                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }

                    @Override
                    public void onFailure(Call<CommanResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext() , t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });

            }
        });
alert.show();

    }
}
