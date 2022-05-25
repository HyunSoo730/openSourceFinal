package com.example.opensourcefinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*

import android.view.View


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val fragmentOne by lazy { dateFragment() }
    private val fragmentTwo by lazy { mainFragment() }
    private val fragmentThree by lazy {memoFragment()}

    private val items = mutableListOf<ContentsModel>()  //데이터 모델 넣을 리스트.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        val currentUser = auth.currentUser


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

        initNavigationBottom()
        changeFragment(fragmentOne)

        setSupportActionBar(main_layout_toolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(true) // 툴바에 타이틀 보이게


        val navView : NavigationView = findViewById(R.id.nav_view)

        //네비게이션 헤더 이메일 세팅
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener{true}
        val header: View = navigationView.getHeaderView(0)
        header.findViewById<TextView>(R.id.email_address).setText(currentUser?.email)



        navView.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.nav_home -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_message -> Toast.makeText(applicationContext, "Clicked Message", Toast.LENGTH_SHORT).show()
                R.id.nav_sync -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_trash -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_login -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_share -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_rate_us -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()

            }
            true

        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                drawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initNavigationBottom() {
        bnv_main.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.first -> {
                        changeFragment(fragmentOne)
                    }
                    R.id.second -> {
                        changeFragment(fragmentTwo)
                    }
                    R.id.third -> {
                        changeFragment(fragmentThree)
                    }
                }
                true
            }


        }
    }

    private fun changeFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit()
    }

    override fun onBackPressed() { //뒤로가기 처리
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers()
            // 테스트를 위해 뒤로가기 버튼시 Toast 메시지
            Toast.makeText(this,"back btn clicked", Toast.LENGTH_SHORT).show()
        } else{
            super.onBackPressed()
        }
    }
}



