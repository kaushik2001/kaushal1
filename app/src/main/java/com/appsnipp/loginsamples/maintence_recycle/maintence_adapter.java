package com.appsnipp.loginsamples.maintence_recycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.CommanResponse;
import com.appsnipp.loginsamples.apiinterface.Paytm.Checksum;
import com.appsnipp.loginsamples.apiinterface.Paytm.Constants;
import com.appsnipp.loginsamples.apiinterface.Paytm.paytm;
import com.appsnipp.loginsamples.apiinterface.responce.mainte_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.mainte_get_set;
import com.appsnipp.loginsamples.bill_recycle.bill_adapter;
import com.appsnipp.loginsamples.bill_recycle.bill_data;
import com.appsnipp.loginsamples.storage.sareprefrencelogin;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class maintence_adapter extends RecyclerView.Adapter<maintence_adapter.ViewHolder> implements PaytmPaymentTransactionCallback {
    Context mcontext;
    String price,mid,mname,s;
    private List<mainte_get_set> maintence_data;
    public maintence_adapter(List<mainte_get_set> maintence_data,Context mcontext){
        this.maintence_data=maintence_data;
        this.mcontext=mcontext;
    }


    @NonNull
    @Override
    public maintence_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.maintence_layout,null);
        ViewHolder viewHolder=new ViewHolder(itemview);
        viewHolder.ma_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= sareprefrencelogin.getInstance(mcontext).getuser();
                s=user.getHouseno();
                //mainte_get_set d=maintence_data.get(i);
                mid=maintence_data.get(viewHolder.getAdapterPosition()).getId()+"";
                mname=maintence_data.get(viewHolder.getAdapterPosition()).getBillname();
                price=maintence_data.get(viewHolder.getAdapterPosition()).getAmount();
                generateCheckSum();

//                Api api= ApiClient.getClient().create(Api.class);
//                Call<CommanResponse> call=api.mainpay_entry("mainpayentry",mid,mname,s);
//                call.enqueue(new Callback<CommanResponse>() {
//                    @Override
//                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
//                        Toast.makeText(mcontext, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<CommanResponse> call, Throwable t) {
//                        Toast.makeText(mcontext, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
            }



        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull maintence_adapter.ViewHolder viewHolder, int i) {
        mainte_get_set d=maintence_data.get(i);

        viewHolder.ma_name.setText(d.getBillname());
        viewHolder.ma_ammount.setText(d.getAmount());
        viewHolder.ma_last_date.setText(d.getLastdate());

    }

    @Override
    public int getItemCount() {
        return maintence_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView ma_name,ma_ammount,ma_last_date;
        public Button ma_pay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ma_name=(TextView) itemView.findViewById(R.id.maintence_name2);
            ma_ammount=(TextView) itemView.findViewById(R.id.maintence_ammount2);
            ma_last_date=(TextView) itemView.findViewById(R.id.maintence_last_date2);
            ma_pay=itemView.findViewById(R.id.maintence_pay);


        }
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
                Toast.makeText(mcontext , t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
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
        Service.startPaymentTransaction(mcontext, true, true, this);

    }

    private void verifyTransactionStatus(String orderId) {

        if(orderId=="TXN_SUCCESS"){
            Api api= ApiClient.getClient().create(Api.class);
                Call<CommanResponse> call=api.mainpay_entry("mainpayentry",mid,mname,s);
                call.enqueue(new Callback<CommanResponse>() {
                    @Override
                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                        Toast.makeText(mcontext, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<CommanResponse> call, Throwable t) {
                        Toast.makeText(mcontext, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

                    }
                });
        }
    }



    //all these overriden method is to detect the payment result accordingly
    @Override
    public void onTransactionResponse(Bundle bundle) {

        Toast.makeText(mcontext,bundle.toString(), Toast.LENGTH_LONG).show();
        String orderId = bundle.getString("STATUS");
        verifyTransactionStatus(orderId);
    }

    @Override
    public void networkNotAvailable() {
        Toast.makeText(mcontext, "Network error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        Toast.makeText(mcontext, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void someUIErrorOccurred(String s) {
        Toast.makeText(mcontext, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Toast.makeText(mcontext, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Toast.makeText(mcontext, "Back Pressed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Toast.makeText(mcontext, s + bundle.toString(), Toast.LENGTH_LONG).show();
    }

}
