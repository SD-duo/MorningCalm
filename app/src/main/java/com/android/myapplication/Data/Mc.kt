package com.android.myapplication.Data


import com.google.gson.annotations.SerializedName

class Mc : ArrayList<Mc.McItem>(){
    data class McItem(
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
        @SerializedName("type")
        val type: String?,
        @SerializedName("quntitiy")
        val quantitiy: Int
    )
}