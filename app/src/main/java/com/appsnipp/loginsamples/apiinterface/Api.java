package com.appsnipp.loginsamples.apiinterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("RegistrationControllerJson.php")
    Call<CommanResponse> createUser(

            @Field("loginRegistration") String loginRegistration,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("roletype") String roletype,
            @Field("mobno") String mobno,
            @Field("email") String email,
            @Field("houseno") String houseno,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("loginapi.php")
    Call<CommanResponse> login(

            @Field("loginmember") String loginmember,
            @Field("mobno") String mobno,
            @Field("password") String password
    );
}
