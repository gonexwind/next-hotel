package com.gonexwind.nexthotel.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gonexwind.nexthotel.core.data.remote.response.Hotel
import com.gonexwind.nexthotel.core.data.remote.response.HotelsResponse
import com.gonexwind.nexthotel.core.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _listHotel = MutableLiveData<List<Hotel>>()
    val listHotel: LiveData<List<Hotel>> = _listHotel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getHotels()
    }

    private fun getHotels() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListHotel()
        client.enqueue(object : Callback<HotelsResponse> {
            override fun onResponse(
                call: Call<HotelsResponse>,
                response: Response<HotelsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listHotel.value = response.body()?.data
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<HotelsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}