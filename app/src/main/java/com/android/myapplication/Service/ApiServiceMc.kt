package com.android.myapplication.Service

import com.android.myapplication.Data.Mc
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceMc {
    @GET("/selectList/all")
    fun getMcdata(

        ): Call<Mc>

}