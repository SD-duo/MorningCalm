package com.android.myapplication.Service

import com.android.myapplication.Data.Mc
import com.android.myapplication.Data.Mc2
import com.android.myapplication.Data.RequestMc
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiServiceMc {
    @GET("material/selectAll")
    fun getMcdata(

        ): Call<Mc2>

    @PUT("material/updateProperty")
    fun updateMcdata(
        @Body requestMc: RequestMc

    ): Call<Void>

}