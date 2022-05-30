package com.example.opensourcefinal

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnFailureListener

import com.google.android.gms.tasks.OnSuccessListener




class Bookmark2Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val bookModel = mutableListOf<BookMarkModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark2)
        auth = Firebase.auth

        //북마크할 것들 리사이클러뷰로 보여주기 위해 리사이클러뷰 관련 변수들!
        val recyclerview = findViewById<RecyclerView>(R.id.BookRV)
        val rvAdapter = BookRVAdapter(this, bookModel)
        recyclerview.adapter = rvAdapter

        recyclerview.layoutManager = LinearLayoutManager(this)


        //LongClick 시 삭제
        rvAdapter.setItemClickListener(object : BookRVAdapter.OnItemClickListener {
            override fun onLongClick(v: View, position: Int) {
                bookModel.removeAt(position)
                Toast.makeText(applicationContext, "삭제했습니다!", Toast.LENGTH_SHORT).show()
                rvAdapter.notifyDataSetChanged()
            }

        })
        recyclerview.adapter = rvAdapter




        //북마크 데이터 불러오기.
        val database = Firebase.database
        val myBookmarkRef = database.getReference("bookmark")  //어느 경로에 저장할거냐고 묻는거야

        //내 uid기반으로 안에 있는 데이터 가져오기
        myBookmarkRef.child(auth.currentUser!!.uid.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    //데이터 가져온 후 이곳에서..
                    for (dataModel in snapshot.children){
//                        BookMarkModel.add(dataModel.getValue(BookMarkModel::class.java)!!)
                        bookModel.add(dataModel.getValue(BookMarkModel::class.java)!!)
                    }
                    //반복문이 끝날때마다 데이터 동기화 시켜주면 돼
                    rvAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    //실패하는 경우에 실행되는 코드
                    Log.d("BookMark", "실패")
                }

            })

    }
}