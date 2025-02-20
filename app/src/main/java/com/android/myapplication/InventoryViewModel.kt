package com.android.myapplication.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myapplication.Data.Mc2
import com.android.myapplication.Service.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Mc2.ResultData.Result>>()
    val items: LiveData<List<Mc2.ResultData.Result>> get() = _items

    private var allItems: List<Mc2.ResultData.Result> = emptyList()

    fun setAllItems(items: List<Mc2.ResultData.Result>) {
        allItems = items
        _items.value = items
    }

    fun getMcData() {
        val service = RetrofitModule.createSonnyApiService()
        val call: Call<Mc2> = service.getMcdata()

        call.enqueue(object : Callback<Mc2> {
            override fun onResponse(call: Call<Mc2>, response: Response<Mc2>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val resultList = responseBody?.resultData?.result?.filterNotNull() ?: emptyList()  // ✅ null 제거

                    setAllItems(resultList)
                    _items.value = resultList  // 🔥 LiveData 업데이트 추가

                    Log.d("InventoryViewModel", "API Response: ${resultList.size} items")
                }
            }

            override fun onFailure(call: Call<Mc2>, t: Throwable) {
                Log.e("InventoryViewModel", "API call failed", t)

            }
        })
    }


    //1.추가 삭제 버튼
    //2.수정 버튼      [ 수정할때 나올 UI ] -> 수정버튼 누르면 해당 리스트에 해당하는 데이터도 같이 팝업 되야함(Edittext로 수정,입력 가능하게)

    //보낼때
    fun postMcData(update: List<Mc2.ResultData.Result>){
        update //


    }
}
