package com.example.opensource

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiData(
    @SerializedName("page") val page : Int,
    @SerializedName("perPage") val perPage : Int,
    @SerializedName("totalCount") val totalCount : Int,
    @SerializedName("currentCount") val currentCount : Int,
    @SerializedName("matchCount") val matchCount : Int,
    @SerializedName("data") val  stores : List<Store>
)


data class Store(
    @SerializedName("업태") val kind : String,
    @SerializedName("업소명") val name : String,
    @SerializedName("소재지(도로명)") val address : String,
    @SerializedName("주취급음식") val mainFood : String,
    @SerializedName("전화번호") val number : String,
    @SerializedName("위도") val latitude : String,
    @SerializedName("경도") val longitude : String,
    @Expose@SerializedName("데이터기준일자") val day : String
){
        override fun toString() : String{
            return "Store : [\n"+
                    "   업태 : ${kind}\n"+
                    "   업소명 : ${name}\n"+
                    "   주소 : ${address}\n"+
                    "   취급 음식 : ${mainFood}\n"+
                    "   전화번호 : ${number}\n"+
                    "   위도 : ${latitude}\n"+
                    "   경도 : ${longitude}\n"
    }
}

