package com.appsnipp.loginsamples.res_book_res;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
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
import com.appsnipp.loginsamples.apiinterface.Paytm.Checksum;
import com.appsnipp.loginsamples.apiinterface.CommanResponse;
import com.appsnipp.loginsamples.apiinterface.Paytm.Constants;
import com.appsnipp.loginsamples.apiinterface.Paytm.paytm;
import com.appsnipp.loginsamples.apiinterface.responce.res_book_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.res_book_get_set;
import com.appsnipp.loginsamples.storage.sareprefrencelogin;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;


import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class book_res_act extends AppCompatActivity implements PaytmPaymentTransactionCallback {
    RecyclerView recyclerView;
    res_book_adapter ev;
    AlertDialog.Builder builder;
    String res_namee,price,book_date,book_time;
    List<res_book_get_set> li;
    SwipeRefreshLayout swipe;
    EditText res_time,res_date;
    Button sv;
    TextView t;
    User user= sareprefrencelogin.getInstance(getApplication()).getuser();
    String book_name=user.getFname()+" "+user.getLname();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_res_act);





        Intent i=getIntent();
        res_namee=i.getStringExtra("res_name");
        price=i.getStringExtra("price");
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
                book_date=res_date.getText().toString().trim();
                book_time=res_time.getText().toString().trim();
                //res_namee for res_name

                Api api= ApiClient.getClient().create(Api.class);
                Call<CommanResponse> call= api.book_check("resourcecheck",res_namee,book_date);
                call.enqueue(new Callback<CommanResponse>() {
                    @Override
                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                        if(response.body().getSuccess()==200){
                            generateCheckSum();
                            alert.dismiss();
                        }
                        else
                            Toast.makeText(getApplicationContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
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
    private void generateCheckSum() {

        //getting the tax amount first.
        String txnAmount = price;//textViewPrice.getText().toString().trim();

        //creating a retrofit object.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating the retrofit api service
        Api apiService = retrofit.create(Api.class);

        //creating paytm object
        //containing all the values required
        final paytm paytm = new paytm(
                Constants.M_ID,
                Constants.CHANNEL_ID,
                txnAmount,
                Constants.WEBSITE,
                Constants.CALLBACK_URL,
                Constants.INDUSTRY_TYPE_ID
        );

        //creating a call object from the apiService
        Call<Checksum> call1 = apiService.getChecksum(
                paytm.getmId(),
                paytm.getOrderId(),
                paytm.getCustId(),
                paytm.getChannelId(),
                paytm.getTxnAmount(),
                paytm.getWebsite(),
                paytm.getCallBackUrl(),
                paytm.getIndustryTypeId()
        );

        call1.enqueue(new Callback<Checksum>() {
            @Override
            public void onResponse(Call<Checksum> call, Response<Checksum> response) {
                //once we get the checksum we will initiailize the payment.
                //the method is taking the checksum we got and the paytm object as the parameter
                initializePaytmPayment(response.body().getChecksumHash(), paytm);
            }

            @Override
            public void onFailure(Call<Checksum> call, Throwable t) {
                Toast.makeText(getApplicationContext() , t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializePaytmPayment(String checksumHash, paytm paytm) {

        //getting paytm service
        PaytmPGService Service = PaytmPGService.getStagingService();

        //use this when using for production
        //PaytmPGService Service = PaytmPGService.getProductionService();

        //creating a hashmap and adding all the values required
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("MID", Constants.M_ID);
        paramMap.put("ORDER_ID", paytm.getOrderId());
        paramMap.put("CUST_ID", paytm.getCustId());
        paramMap.put("CHANNEL_ID", paytm.getChannelId());
        paramMap.put("TXN_AMOUNT", paytm.getTxnAmount());
        paramMap.put("WEBSITE", paytm.getWebsite());
        paramMap.put("CALLBACK_URL", paytm.getCallBackUrl());
        paramMap.put("CHECKSUMHASH", checksumHash);
        paramMap.put("INDUSTRY_TYPE_ID", paytm.getIndustryTypeId());


        //creating a paytm order object using the hashmap
        PaytmOrder order = new PaytmOrder(paramMap);

        //intializing the paytm service
        Service.initialize(order, null);

        //finally starting the payment transaction
        Service.startPaymentTransaction(this, true, true, this);

    }

//        @Override
//    public void onTransactionResponse(Bundle inResponse) {
//
//    }
//
//    @Override
//    public void networkNotAvailable() {
//
//    }
//
//    @Override
//    public void clientAuthenticationFailed(String inErrorMessage) {
//
//    }
//
//    @Override
//    public void someUIErrorOccurred(String inErrorMessage) {
//
//    }
//
//    @Override
//    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
//
//    }
//
//    @Override
//    public void onBackPressedCancelTransaction() {
//
//    }
//
//    @Override
//    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
//
//    }

    private void verifyTransactionStatus(String orderId) {

        if(orderId=="TXN_SUCCESS"){
            Api api= ApiClient.getClient().create(Api.class);
            Call<CommanResponse> call= api.book_res("resourceregi",res_namee,book_date,book_time,book_name);
            call.enqueue(new Callback<CommanResponse>() {
                @Override
                public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<CommanResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext() , t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //all these overriden method is to detect the payment result accordingly
    @Override
    public void onTransactionResponse(Bundle bundle) {

        Toast.makeText(this,bundle.toString(), Toast.LENGTH_LONG).show();
        String orderId = bundle.getString("STATUS");
        verifyTransactionStatus(orderId);
    }

    @Override
    public void networkNotAvailable() {
        Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void someUIErrorOccurred(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Toast.makeText(this, "Back Pressed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Toast.makeText(this, s + bundle.toString(), Toast.LENGTH_LONG).show();
    }
}
