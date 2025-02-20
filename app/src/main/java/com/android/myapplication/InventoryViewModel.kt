package com.android.myapplication.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myapplication.Data.Mc
import com.android.myapplication.Service.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Mc.McItem>>()
    val items: LiveData<List<Mc.McItem>> get() = _items

    // 전체 아이템을 저장하는 변수
    private var allItems: List<Mc.McItem> = emptyList()

    // API에서 받은 데이터를 저장하고 필터링을 초기화
    fun setAllItems(items: List<Mc.McItem>) {
        allItems = items
        _items.value = items
    }


    // API 호출
    fun getMcData() {
        val service = RetrofitModule.createSonnyApiService()
        val call: Call<Mc> = service.getMcdata()

        call.enqueue(object : Callback<Mc> {
            override fun onResponse(call: Call<Mc>, response: Response<Mc>) {
                if (response.isSuccessful) {
                    val responseBody = response.body() ?: emptyList()

                    setAllItems(responseBody)  // 데이터 설정
                    _items.value = responseBody // 🔥 LiveData 업데이트 추가

                    Log.d("InventoryViewModel", "API Response: $responseBody")
                }
            }

            override fun onFailure(call: Call<Mc>, t: Throwable) {
                // 네트워크 에러 처리
            }
        })
    }
}
