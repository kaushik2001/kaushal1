package com.appsnipp.loginsamples.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;


public class sareprefrencelogin {
    private static sareprefrencelogin minstance;
    private Context mctx;
    public static final String MyPREFERENCES = "MyPrefs" ;

    public sareprefrencelogin(Context mctx) {
        this.mctx = mctx;
    }
    public static synchronized sareprefrencelogin getInstance(Context mctx)
    {
        if(minstance==null)
        {
            minstance= new sareprefrencelogin(mctx);

        }
    return minstance;
    }



    public void saveuser(User user)
    {
        SharedPreferences sharedPreferences=mctx.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("id",user.getId());
        editor.putString("fname",user.getFname());
        editor.putString("lname",user.getLname());
        editor.putString("mobno",user.getMobno());
        editor.apply();
    }
    public boolean islogin(){
SharedPreferences sharedPreferences=mctx.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
return sharedPreferences.getInt("id",-1)!= -1;
    }
    public User getuser()
    {
        SharedPreferences sharedPreferences=mctx.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id",-1),
        sharedPreferences.getString("fname",null),
        sharedPreferences.getString("lname",null),
        sharedPreferences.getString("mobno",null)



        );
    }
    public void clear()
    {
        SharedPreferences sharedPreferences=mctx.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }
}
