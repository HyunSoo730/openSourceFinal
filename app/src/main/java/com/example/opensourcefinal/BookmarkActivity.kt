package com.example.opensourcefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BookmarkActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)


        auth = Firebase.auth

        val database = Firebase.database
        val myBookmarkRef = database.getReference("bookmark") //어느 경로에 저장할거냐고 묻는거야

        val Name = intent.getStringExtra("NAME").toString()
        val LOC = intent.getStringExtra("LOC").toString()

        val goBook = findViewById<TextView>(R.id.bookBtn)
        goBook.setOnClickListener {
            myBookmarkRef.child(auth.currentUser!!.uid).push().setValue(BookMarkModel(Name, LOC))
            //그리고 북마크 페이지로 넘어가자
            val intent = Intent(this,Bookmark2Activity::class.java)
            startActivity(intent)
        }

        //모든 정보 다 받아오기.
        //웹뷰 연결까지 끝.


//        웹뷰에서는 북마크 X
//        val saveBtn = findViewById<TextView>(R.id.saveBtn)
//        saveBtn.setOnClickListener {
//            Toast.makeText(this, "북마크 저장 완료", Toast.LENGTH_LONG).show()
//            myBookmarkRef.child(auth.currentUser!!.uid).push()   //내 uid기반으로 저장해야함. push() 항상 써줘야 됨
//                .setValue(MangoModel(url, imageUrl, title))    //ContentModel전체를 저장해서 갖다 쓰자
//        }

    }
}