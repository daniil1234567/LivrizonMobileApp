package com.app.livrizon.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ViewModel(application: Application) : AndroidViewModel(application) {

    val choose: MutableLiveData<Int?> by lazy {
        MutableLiveData(null)
    }
    val action: MutableLiveData<Boolean?> by lazy {
        MutableLiveData(null)
    }
    val search: MutableLiveData<String> by lazy {
        MutableLiveData(null)
    }
}