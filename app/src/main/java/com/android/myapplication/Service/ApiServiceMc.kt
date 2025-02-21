package com.android.myapplication.Service

import com.android.myapplication.Data.Mc
import com.android.myapplication.Data.Mc2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServiceMc {
    @GET("/selectList/all")
    fun getMcdata(

        ): Call<Mc2>

    @POST
    fun postMcdata(

    ): Call<Mc2>

}