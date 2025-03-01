package com.android.myapplication.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myapplication.Data.Mc2
import com.android.myapplication.Data.RequestMc
import com.android.myapplication.Service.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryViewModel : ViewModel() {
    //commit test
    private val _items = MutableLiveData<List<Mc2.ResultData.Result>>()
    val items: LiveData<List<Mc2.ResultData.Result>>
        get() = _items


    fun getMcData() {
        val service = RetrofitModule.createSonnyApiService()
        val call: Call<Mc2> = service.getMcdata()

        call.enqueue(object : Callback<Mc2> {
            override fun onResponse(call: Call<Mc2>, response: Response<Mc2>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val resultList =
                        responseBody?.resultData?.result?.filterNotNull() ?: emptyList()

                    _items.value = resultList!!
                    Log.d("InventoryViewModel", "getMcData()가 호출됨")

                }
            }

            override fun onFailure(call: Call<Mc2>, t: Throwable) {
                Log.e("InventoryViewModel", "API call failed", t)
            }
        })
    }

    fun postMcData(updatedItem: RequestMc) {
        val service = RetrofitModule.createSonnyApiService()
        val call: Call<Void> = service.updateMcdata(updatedItem)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("InventoryViewModel", "Item updated successfully!")

                    // 데이터를 다시 불러와서 LiveData에 업데이트
                    getMcData()

                } else {
                    Log.e("InventoryViewModel", "Update failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("InventoryViewModel", "API call failed", t)
            }
        })
    }
}

