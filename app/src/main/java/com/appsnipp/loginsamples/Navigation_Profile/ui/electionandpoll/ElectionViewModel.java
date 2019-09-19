package com.appsnipp.loginsamples.Navigation_Profile.ui.electionandpoll;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ElectionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ElectionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is election fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}