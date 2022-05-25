package com.example.opensourcefinal

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class YongApiData(
    @SerializedName("page") val page : Int,
    @SerializedName("perPage") val perPage : Int,
    @SerializedName("totalCount") val totalCount : Int,
    @SerializedName("currentCount") val currentCount : Int,
    @SerializedName("matchCount") val matchCount : Int,
    @SerializedName("data") val  stores : List<YongStore>
)


data class YongStore(
    @SerializedName("종류(01한식,02중식,03일식,04양식,05기타외국음식,06디저트/카페)") val kind : Int,
    @SerializedName("테마(국가)") val thema : String,
    @SerializedName("업소명") val name : String,
    @SerializedName("전화번호") val number : String,
    @SerializedName("주소1") val address1 : String,
    @SerializedName("주소2") val address2 : String,
    @SerializedName("주요요리") val mainFood : String,
    @SerializedName("위도") val latitude : String,
    @SerializedName("경도") val longitude : String,
    @Expose @SerializedName("사장님이자랑하는내가게한마디") val day : String
){
    override fun toString() : String{
        return "YongStore : [\n"+
                "   업태 : ${kind}\n"+
                "   업소명 : ${name}\n"+
                "   주소 : ${address1}\n"+
                "   취급 음식 : ${mainFood}\n"+
                "   전화번호 : ${number}\n"+
                "   위도 : ${latitude}\n"+
                "   경도 : ${longitude}\n"
    }
}


