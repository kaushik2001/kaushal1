package com.appsnipp.loginsamples.Navigation_Profile.ui.complain;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ComplainViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ComplainViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is complain fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}