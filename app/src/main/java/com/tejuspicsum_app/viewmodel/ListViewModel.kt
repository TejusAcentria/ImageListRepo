package com.tejuspicsum_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tejuspicsum_app.apiHelper.RetrofitHelper
import com.tejuspicsum_app.model.ImageResponse
import retrofit2.Callback

class ListViewModel(application: Application) : AndroidViewModel(application) {




    fun getListData(): MutableLiveData<ArrayList<ImageResponse>> {
        return RetrofitHelper.instance!!.getListAPi(getApplication())
    }
}