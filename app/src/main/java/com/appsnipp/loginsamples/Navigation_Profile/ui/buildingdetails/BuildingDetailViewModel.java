package com.appsnipp.loginsamples.Navigation_Profile.ui.buildingdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class BuildingDetailViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BuildingDetailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Building fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}