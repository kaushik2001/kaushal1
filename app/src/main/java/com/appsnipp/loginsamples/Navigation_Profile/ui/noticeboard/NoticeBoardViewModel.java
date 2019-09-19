package com.appsnipp.loginsamples.Navigation_Profile.ui.noticeboard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class NoticeBoardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NoticeBoardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notice fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}