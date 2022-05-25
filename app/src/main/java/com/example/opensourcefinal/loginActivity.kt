package com.example.opensourcefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.opensourcefinal.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class loginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //비밀번호 기반 계정 만들기를 하기 위해서 먼저
        auth = Firebase.auth   //파이어베이스 auth객체의 공유 인스턴스를 가져온 이후에

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            //회원가입 버튼을 누르면 !!! 비밀번호 기반 계정 만들기. 구글링

            val email = findViewById<EditText>(R.id.emailArea)
            val password = findViewById<EditText>(R.id.passwordArea)
            //신규 사용자의 이메일 주소와 비밀번호의 텍스트(toString()을 통해)가져와서 밑에 함수에 전달 하여 신규계정 생성!
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->   //이메일 비번의 텍스트를 매개변수로 넣어주면 돼
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //회원가입 성공하면 MainActivity로 넘겨주자 Intent사용
                        Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_LONG).show()


                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else {  //회원가입 실패시 작성할 것
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "회원가입 실패, 다시 시도해주세요!", Toast.LENGTH_LONG).show()
                        Log.w("JOINACTIVITY", "createUserWithEmail:failure", task.exception)

                    }
                }

        }
    }
}