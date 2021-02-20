package com.tejuspicsum_app.apiHelper

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tejuspicsum_app.ApiService.ApiService
import com.tejuspicsum_app.apis.WebApi
import com.tejuspicsum_app.model.ImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitHelper {
    fun getHttpClient(context: Context?): WebApi? {
        return ApiService.getRetrofitObj(context!!)
    }

    companion object {
        private var mRetrofitService: RetrofitHelper? = null
        @get:Synchronized
        val instance: RetrofitHelper?
            get() {
                if (mRetrofitService == null) {
                    mRetrofitService = RetrofitHelper()
                }
                return mRetrofitService
            }
    }


    fun getListAPi(context:Context): MutableLiveData<ArrayList<ImageResponse>> {

        val mutableLiveData = MutableLiveData<ArrayList<ImageResponse>>()

        val networkInterface = getHttpClient(context)
        
            networkInterface!!.getList().enqueue(object :Callback<ArrayList<ImageResponse>>{
                override fun onFailure(call: Call<ArrayList<ImageResponse>>, t: Throwable) {
                    mutableLiveData.setValue(null)
                }

                override fun onResponse(
                    call: Call<ArrayList<ImageResponse>>,
                    response: Response<ArrayList<ImageResponse>>
                ) {

                    if (response.isSuccessful){

                        val body = response.body()
                        if (body == null)
                        {
                            mutableLiveData.setValue(null)
                            return
                        }
                        mutableLiveData.setValue(body)
                    }
                }

            })


        return mutableLiveData
    }
}