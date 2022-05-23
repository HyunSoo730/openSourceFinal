package com.example.opensourcefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opensourcefinal.R
import com.example.opensourcefinal.WebViewActivity

class MangoActivity : AppCompatActivity() {

    private val items = mutableListOf<MangoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mango)


        //웹뷰를 이용해서 할 건데 리스트 표시는 리사이클러뷰로 사진과 텍스트 함께 보여주자
        //리사이클러뷰에 데이터 넣어서 연결하기.
        items.add(MangoModel("https://www.mangoplate.com/top_lists/2725_seoul2021", "https://mp-seoul-image-production-s3.mangoplate.com/281547/753280_1550146766591_11966", "서울 맛집"))
        items.add(MangoModel("https://www.mangoplate.com/top_lists", "https://mp-seoul-image-production-s3.mangoplate.com/571787_1651663729806924.jpg", "믿고 보는 맛집 리스트"))
        items.add(MangoModel("https://www.mangoplate.com/top_lists/2961_gangnam2022", "https://mp-seoul-image-production-s3.mangoplate.com/1047892_1648713014222945.jpg", "2022 강남 맛집 베스트"))
        //웹 이미지 Glide사용하자
        
        


        //리사이클럽 데이터 연결   Glide를 위해 context도 같이 전달
        val recyclerview = findViewById<RecyclerView>(R.id.mangoRV)
        val rvAdapter = MangoRVAdapter(baseContext,items)  //리스트 넣어준 후 연결
        recyclerview.adapter = rvAdapter

        recyclerview.layoutManager = LinearLayoutManager(this)


        //***************************************************//
        //이 부분이 리사이클러뷰에서 클릭 이벤트 처리 부분.
        //아이템이 클릭되면 새로운 뷰로 넘겨주자  --> WebViewActivity로 넘겨주자
        //WebViewActivity에서 웹뷰 달아줘야함.
        rvAdapter.itemClick = object : MangoRVAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                //이미지 클릭시 새로운 액티비티로 넘겨줄꺼야. 거기서 웹뷰로 이동하도록 달아주자!
                //웹뷰 태그 사용
                //만약 이미지 클릭을 하면!
                val intent = Intent(baseContext, WebViewActivity::class.java)
                //viewActivity로 이동을 한 후 !
                //아이템마다 각각의 url을 보내줘야하잖아  +  url 뿐만 아니라 title, imageUrl도 같이 주자.
                intent.putExtra("url", items[position].url)  //url부분 보내야하잖아
                intent.putExtra("title", items[position].titleText)
                intent.putExtra("imageUrl", items[position].ImageUrl)

                startActivity(intent) // 그 내용 실행해 !
            }
        }
        
        
        
    }

}