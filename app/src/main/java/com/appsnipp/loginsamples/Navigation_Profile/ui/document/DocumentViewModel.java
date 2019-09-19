package com.appsnipp.loginsamples.Navigation_Profile.ui.document;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class DocumentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DocumentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is document fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}