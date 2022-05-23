package com.example.opensourcefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //스플래쉬화면에서 파이어베이스 인증 권한 가져와서 검사 시행.

        //비밀번호 기반 계정 만들기를 하기 위해서 먼저
        auth = Firebase.auth   //파이어베이스 auth객체의 공유 인스턴스를 가져온 이후에


        //로그인 화면을 그냥 계속 띄울지 말지?? 설정???


        //활동을 초기화할 때 사용자가 현재 로그인되어 있는지 확인하는 코드

//        val currentUser = auth.currentUser
//        Handler().postDelayed({
//                  startActivity(Intent(this, loginActivity::class.java))
//                  finish()
//           }, 3000)   //회원가입이 안되어있으므로 splash화면 잠깐 보여주고 로그인화면으로 넘겨

        val currentUser = auth.currentUser
        if(currentUser == null){
            //회원가입이 안되어있으므로, loginActivity로 이동해서 회원가입 진행
            Toast.makeText(this, "회원가입을 아직 하지 않으셨습니다!", Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                startActivity(Intent(this, loginActivity::class.java))
                finish()
            }, 3000)   //회원가입이 안되어있으므로 splash화면 잠깐 보여주고 로그인화면으로 넘겨
         }
        else{
            //회원가입이 되어있으므로 MainActivity로 이동! 대신 메시지 추가해주고?
            Toast.makeText(this, "회원가입을 이미 한 사용자입니다!", Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000)
        }

//        //스플래쉬화면 불러오기
//        Handler().postDelayed({
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)

    }
}