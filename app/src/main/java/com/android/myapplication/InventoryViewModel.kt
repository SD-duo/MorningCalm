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
        filterItems(0)  // 첫 번째 탭에 맞는 데이터로 필터링
    }

    // 탭 인덱스에 맞게 데이터를 필터링
    fun filterItems(tabIndex: Int) {
        val filteredItems = when (tabIndex) {
            0 -> allItems.filter { it.category == "1" }
            1 -> allItems.filter { it.category == "2" }
            2 -> allItems.filter { it.category == "3" }
            3 -> allItems.filter { it.category == "4" }
            else -> emptyList()
        }
        Log.d("InventoryViewModel", "Filtering for tabIndex: $tabIndex, Found: ${filteredItems.size} items")
        _items.value = filteredItems

        Log.d("InventoryViewModel", "items LiveData Updated! Size: ${filteredItems.size}")

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

                    Log.d("InventoryViewModel", "API Response: $responseBody")
                }
            }

            override fun onFailure(call: Call<Mc>, t: Throwable) {
                // 네트워크 에러 처리
            }
        })
    }
}
