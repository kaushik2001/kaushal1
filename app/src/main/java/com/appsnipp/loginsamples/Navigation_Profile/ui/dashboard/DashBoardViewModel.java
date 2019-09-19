package com.appsnipp.loginsamples.Navigation_Profile.ui.dashboard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class DashBoardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashBoardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}