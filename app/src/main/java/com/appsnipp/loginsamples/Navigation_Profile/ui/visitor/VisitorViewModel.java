package com.appsnipp.loginsamples.Navigation_Profile.ui.visitor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class VisitorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VisitorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Visitor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}