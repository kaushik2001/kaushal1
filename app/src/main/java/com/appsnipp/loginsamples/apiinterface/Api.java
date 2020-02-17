package com.appsnipp.loginsamples.apiinterface;

import com.appsnipp.loginsamples.apiinterface.Paytm.Checksum;
import com.appsnipp.loginsamples.apiinterface.responce.event_responce;
import com.appsnipp.loginsamples.apiinterface.responce.loginresponce;
import com.appsnipp.loginsamples.apiinterface.responce.member_responce;
import com.appsnipp.loginsamples.apiinterface.responce.notice_responce;
import com.appsnipp.loginsamples.apiinterface.responce.res_book_responce;
import com.appsnipp.loginsamples.apiinterface.responce.resource_responce;
import com.appsnipp.loginsamples.apiinterface.responce.visidetail_responce;

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
    Call<loginresponce> login(

            @Field("loginmember") String loginmember,
            @Field("mobno") String mobno,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("noticeapi.php")
    Call<notice_responce> noticedetail(
            @Field("noticedetail") String noticedetail
    );


    @FormUrlEncoded
    @POST("eventapi.php")
    Call<event_responce> eventdetail(
            @Field("eventdetail") String eventdetail
    );


    @FormUrlEncoded
    @POST("resourceapi.php")
    Call<resource_responce> resourcedetail(
            @Field("resourcedetail") String resourcedetail
    );

    @FormUrlEncoded
    @POST("GatekeeperVisiEntry.php")
    Call<visidetail_responce> visidetail(
            @Field("gatekvisidetail") String gatekvisidetail

    );



    @FormUrlEncoded
    @POST("membersapi.php")
    Call<member_responce> membersdetail(
            @Field("membersdetail") String membersdetail,
            @Field("blockno") String blockno
    );


    @FormUrlEncoded
    @POST("forgrtpass.php")
    Call<CommanResponse> changepass(
            @Field("changepass") String changepass,
            @Field("mobno") String mobno,
            @Field("password") String password

    );


    @FormUrlEncoded
    @POST("forgrtpass.php")
    Call<CommanResponse> mobnoex(
            @Field("passmobnoex") String passmobnoex,
            @Field("mobno") String mobno

    );


    @FormUrlEncoded
    @POST("resourseregister.php")
    Call<res_book_responce> booklist(
            @Field("resourcedetails") String resourcedetails,
            @Field("res_name") String res_name

    );


    @FormUrlEncoded
    @POST("resourseregister.php")
    Call<CommanResponse> book_res(
            @Field("resourceregi") String resourceregi,
            @Field("res_name") String res_name,
            @Field("date") String date,
            @Field("time") String time,
            @Field("bookname") String book_name

    );

    @FormUrlEncoded
    @POST("resourseregister.php")
    Call<CommanResponse> book_check(
            @Field("resourcecheck") String resourcecheck,
            @Field("res_name") String res_name,
            @Field("date") String date
    );


    @FormUrlEncoded
    @POST("generateChecksum.php")
    Call<Checksum> getChecksum(
            @Field("MID") String mId,
            @Field("ORDER_ID") String orderId,
            @Field("CUST_ID") String custId,
            @Field("CHANNEL_ID") String channelId,
            @Field("TXN_AMOUNT") String txnAmount,
            @Field("WEBSITE") String website,
            @Field("CALLBACK_URL") String callbackUrl,
            @Field("INDUSTRY_TYPE_ID") String industryTypeId
    );
}
