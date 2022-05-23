package com.example.opensourcefinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opensourcefinal.R

class MainActivity : AppCompatActivity() {

    private val items = mutableListOf<ContentsModel>()  //데이터 모델 넣을 리스트.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        //Fragment만들고 네비게이션뷰에 연결하기
        //+ Fragment에서 버튼(텍스트??)클릭시 이동하기
        //리사이클러뷰 만들기


        //SplashActivity만들기
        //splashActivity까지 했으면 메인에 컨텐츠까지 추가해놓자 이미지 + 텍스트?? (데이터모델)
        //리사이클러뷰로 만들기. --> 네비게이션 뷰 쓰기 때문에 Fragment에 각각 해줘야해 아이템 추가도 각 기능부에서
        //fragment_main에서 리사이클러뷰 만들었기 때문에 mainFragment기능부에서 리사이클러뷰 어댑터 연결해주기

        //MainFragment다 했으면 각 음식점 클릭하는 클릭이벤트 추가해놓자. (API써서 리스트 보이도록 해야)
        //일단 클릭하면 빈 화면으로 이동하게 빈 화면만 연결 (APIActvity)


        //그거 다 했으면 이메일 로그인 + 파이어베이스 연동하기.
        //SplashActivity에서 회원가입 한 사람인지 아닌지 사용자의 uid로 검사 시행해야함.
        //스플래쉬 화면에서 회원가입을 헀으면 메인 페이지로 이동. 이제 메인에서 구현할 기능들 얘기해서 추가해야함.
        //loginActivity가 끝났으면 북마크 기능할 지 말 지 생각.

    }
}