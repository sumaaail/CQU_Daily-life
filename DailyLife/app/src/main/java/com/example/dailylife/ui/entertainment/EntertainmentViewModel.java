package com.example.dailylife.ui.entertainment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntertainmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EntertainmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is e fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}