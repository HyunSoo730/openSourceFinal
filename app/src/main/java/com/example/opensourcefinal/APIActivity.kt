package com.example.opensourcefinal

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opensource.GwangRvAdapter
import com.example.opensource.RetrofitObject.getApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.android.synthetic.main.activity_apiactivity.*
import kotlinx.android.synthetic.main.activity_apiactivity.animation_view
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIActivity : AppCompatActivity() , OnMapReadyCallback {

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap

    private lateinit var auth : FirebaseAuth

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apiactivity)


        //북마크 기능 추가. LongClick 시 팝업창 띄워서 하자!

        auth = Firebase.auth




        
        //어디 맛집인지 데이터(인덱스) 받아오기
        val location : Int = intent.getIntExtra("location",0);



        //네이버 지도 객체 받아오기?  (분석 필요)
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also{
                fm.beginTransaction().add(R.id.map,it).commit()
            }
        mapFragment.getMapAsync(this)
        //----------------------------------
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        //-------------------------------------------------------



        if(location == 0){
            getStoreStatus()        //광진구 Api 데이터 불러오기
        }
        else if(location == 1){
            getYongStoreStatus()    //용산구 Api 데이터 불러오기
        }


    }


    //----------gps 권한 요청 함수----------
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    //----------지도가 실행될 때 호출되는 함수-------------
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource

        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true       //gps 버튼 활성화

    }





    //광진구 맛집 데이터 불러오기
    private fun getStoreStatus(){
        //밑에 함수 Page는 무시하고, PerPage는 음식점 데이터 개수 설정하는 거 (너무 많으면 렉걸리더라)
        getApiService().getInfo(1,30)            //데이터 불러오기(광진구)
            .enqueue(object : Callback<ApiData> {
                override fun onResponse(call: Call<ApiData>, response: Response<ApiData>) {     //로딩에 성공했을 때 실행
                    setResponseText(response.code(), response.body())   //로딩 상태를 표시하는 (아래) 함수 (중요X)
                    Toast.makeText(applicationContext,"데이터를 성공적으로 불러왔습니다.", Toast.LENGTH_SHORT).show()


                    val items = response.body()?.stores as List<Store>      //맛집 데이터 items에 저장
                    //---------리싸이클러뷰 처리----------
                    val rv = findViewById<RecyclerView>(R.id.storeRv)   //리사이클러뷰 불러오기
                    val rvAdapter = GwangRvAdapter(items)
                    rv.layoutManager = LinearLayoutManager(this@APIActivity)

                    //-----클릭 이벤트-----
                    rvAdapter.setItemClickListener(object : GwangRvAdapter.OnItemClickListener{
//                        override fun onClick(v: View, position: Int) {
//                            Toast.makeText(v.context,items[position].name,Toast.LENGTH_SHORT).show()
//
//                            //카메라를 클릭한 음식점 위치로 이동 및 확대
//                            val cameraUpdate = CameraUpdate.scrollAndZoomTo(LatLng(items[position].latitude.toDouble(), items[position].longitude.toDouble()),16.0)
//
//
//                            naverMap.moveCamera(cameraUpdate)
//                        }
                        override fun onClick(v: View, position: Int) {
                            //그냥 클릭 시
                            Toast.makeText(v.context,items[position].name,Toast.LENGTH_SHORT).show()
                        //
                        //                            카메라를 클릭한 음식점 위치로 이동 및 확대
                            val cameraUpdate = CameraUpdate.scrollAndZoomTo(LatLng(items[position].latitude.toDouble(), items[position].longitude.toDouble()),16.0)


                            naverMap.moveCamera(cameraUpdate)
}
                        override fun onLongClick(v: View, position: Int) {
                            //롱 클릭 시

//
                            animation_view.visibility = View.VISIBLE
                            animation_view.playAnimation()
                            Handler().postDelayed({
                                Toast.makeText(baseContext, "북마크 저장 성공", Toast.LENGTH_SHORT).show()
                                animation_view.visibility = View.GONE
                            },2000)



                            //하트 표시 됐으니 북마크 저장해야함.
                            val intent = Intent(baseContext, BookmarkActivity::class.java)
                            intent.putExtra("NAME", items[position].name)
                            intent.putExtra("LOC", items[position].address)    //주소와 이름만 보내.
                            //일단 데이터 보내??
                            Handler().postDelayed({
                                startActivity(intent)
                                finish()
                            },2000)




                        }
                    })
                    rvAdapter.notifyDataSetChanged()
                    rv.adapter = rvAdapter

                    //--------지도 마커 처리---------
                    val markers = mutableListOf(Marker())
                    for(i in items.indices){
                        markers.add(Marker())
                        markers[i].position = LatLng(items[i].latitude.toDouble(),items[i].longitude.toDouble())
                        markers[i].icon = OverlayImage.fromResource(R.drawable.map_maker)
                        markers[i].captionText = items[i].name
                        markers[i].captionColor = Color.parseColor("#FF5E00")
                        markers[i].map = naverMap

                    }

                }

                override fun onFailure(call: Call<ApiData>, t: Throwable) {     //로딩에 실패했을 때 실행
                    Toast.makeText(applicationContext,"데이터 불러오기를 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            })

    }



    //용산구 맛집 데이터 불러오기
    private fun getYongStoreStatus(){
        //밑에 함수 Page는 무시하고, PerPage는 음식점 데이터 개수 설정하는 거 (너무 많으면 렉걸리더라)
        YongRetrofitObject.getApiService().getInfo(1,30)            //데이터 불러오기(광진구)
            .enqueue(object : Callback<YongApiData> {
                override fun onResponse(call: Call<YongApiData>, response: Response<YongApiData>) {     //로딩에 성공했을 때 실행
                    //setResponseText(response.code(), response.body())   //로딩 상태를 표시하는 (아래) 함수 (중요X)
                    Toast.makeText(applicationContext,"데이터를 성공적으로 불러왔습니다.", Toast.LENGTH_SHORT).show()


                    val items = response.body()?.stores as List<YongStore>      //맛집 데이터 items에 저장
                    Log.d("ITEMS!!", items.toString())

                    //---------리싸이클러뷰 처리----------
                    val rv = findViewById<RecyclerView>(R.id.storeRv)   //리사이클러뷰 불러오기
                    rv.layoutManager = LinearLayoutManager(this@APIActivity)
                    val rvAdapter = YongRvAdapter(items)





                    rvAdapter.setItemClickListener(object : YongRvAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int) {
                            //그냥 클릭 시
                            Toast.makeText(v.context,items[position].name,Toast.LENGTH_SHORT).show()
//
//                            카메라를 클릭한 음식점 위치로 이동 및 확대
                            val cameraUpdate = CameraUpdate.scrollAndZoomTo(LatLng(items[position].latitude.toDouble(), items[position].longitude.toDouble()),16.0)


                            naverMap.moveCamera(cameraUpdate)
                        }
                        override fun onLongClick(v: View, position: Int) {
                            //롱 클릭 시

//
                            animation_view.visibility = View.VISIBLE
                            animation_view.playAnimation()
                            Handler().postDelayed({
                                Toast.makeText(baseContext, "북마크 저장 성공", Toast.LENGTH_SHORT).show()
                                animation_view.visibility = View.GONE
                            },2000)



                            //하트 표시 됐으니 북마크 저장해야함.
                            val intent = Intent(baseContext, BookmarkActivity::class.java)
                            intent.putExtra("NAME", items[position].name)
                            intent.putExtra("LOC", items[position].address1)    //주소와 이름만 보내.
                            //일단 데이터 보내??
                            Handler().postDelayed({
                                startActivity(intent)
                                finish()
                            },2000)




                        }

                    })
                    rvAdapter.notifyDataSetChanged()
                    rv.adapter = rvAdapter



                    //--------지도 마커 처리---------
                    val markers = mutableListOf(Marker())
                    for(i in items.indices){
                        markers.add(Marker())
                        markers[i].position = LatLng(items[i].latitude.toDouble(),items[i].longitude.toDouble())
                        markers[i].icon = OverlayImage.fromResource(R.drawable.map_maker)
                        markers[i].captionText = items[i].name
                        markers[i].captionColor = Color.parseColor("#FF5E00")
                        markers[i].map = naverMap

                    }

                }

                override fun onFailure(call: Call<YongApiData>, t: Throwable) {     //로딩에 실패했을 때 실행
                    Toast.makeText(applicationContext,"데이터 불러오기를 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            })

    }
    

    private fun setResponseText(resCode : Int, data : ApiData?){
        when(resCode){
            200->{
                if(data == null){
                    Log.d("data가 null입니다","")
                }
                else{
                    Log.d("성공",data.stores.toString())
                }
            }
            400->{
                Log.d("실패", "인증 정보가 틀렸습니다")
            }
            500->{
                Log.d("실패","API서버가 불안정합니다")
            }
            else->{
                Log.d("기타 문제발생..","")
            }
        }
    }

    private fun showPopup(v: View) {
        val popup = PopupMenu(this, v) // PopupMenu 객체 선언
        popup.menuInflater.inflate(R.menu.list_popup, popup.menu) // 메뉴 레이아웃 inflate
        popup.show() // 팝업 보여주기
    }


}