package com.appsnipp.loginsamples.Navigation_Profile.ui.resource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ResourceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ResourceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is resouce fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}