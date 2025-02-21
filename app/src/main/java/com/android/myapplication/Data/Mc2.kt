package com.android.myapplication.Data


import com.google.gson.annotations.SerializedName

data class Mc2(
    @SerializedName("resultCode")
    val resultCode: String?, //정상 200 //실패 404 // 서버뻑감 500
    @SerializedName("resultData")
    val resultData: ResultData?,
    @SerializedName("resultMessage")
    val resultMessage: String?
) {
    data class ResultData(
        @SerializedName("result")
        val result: List<Result?>?
    ) {
        data class Result(
            @SerializedName("category")
            val category: String?,
            @SerializedName("code")
            val code: String?,
            @SerializedName("diameter")
            val diameter: Double?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("length")
            val length: Double?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("quantity")
            val quantity: Int?,
            @SerializedName("type")
            val type: String?
        )
    }
}