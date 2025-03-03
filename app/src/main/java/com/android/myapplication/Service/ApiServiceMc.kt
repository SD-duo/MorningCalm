package com.android.myapplication.Service

import com.android.myapplication.Data.DeleteMc
import com.android.myapplication.Data.InsertMc
import com.android.myapplication.Data.Mc2
import com.android.myapplication.Data.UpdateMc
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceMc {
    @GET("material/selectAll")
    fun getMcdata(

        ): Call<Mc2>

    @PUT("material/updateProperty")
    fun updateMcdata(
        @Body requestMc: UpdateMc

    ): Call<Void>

    @DELETE("material/deleteProperty")
    fun deleteMcdata(@Query("id") id: Int): Call<Void>

    @POST("material/insertProperty")
    fun insertMcdata(
        @Body insertMc: InsertMc
    ): Call<Void>

    @PUT("material/updateQuantity")
    fun usedMcdata(
        @Body requestMc: UpdateMc
    ): Call<Void>


}