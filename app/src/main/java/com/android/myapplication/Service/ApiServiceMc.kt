package com.android.myapplication.Service

import com.android.myapplication.Data.DeleteMc
import com.android.myapplication.Data.Mc2
import com.android.myapplication.Data.UpdateMc
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT

interface ApiServiceMc {
    @GET("material/selectAll")
    fun getMcdata(

        ): Call<Mc2>

    @PUT("material/updateProperty")
    fun updateMcdata(
        @Body requestMc: UpdateMc

    ): Call<Void>

    @DELETE("material/deleteProperty")
    fun deleteMcdata(
        @Body deleteMc: DeleteMc
    ): Call<Void>

}