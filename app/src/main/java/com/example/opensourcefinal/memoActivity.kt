package com.example.opensourcefinal
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.opensourcefinal.memoDataModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class memoActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private val dataList = mutableListOf<memoDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        auth = Firebase.auth

        val database = Firebase.database
        val myRef = database.getReference("message")

        val listview = findViewById<ListView>(R.id.lv)
        val adapter_list = memoListViewAdapter(dataList)
        listview.adapter = adapter_list

        myRef.child(auth.currentUser?.uid.toString())
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(dataModel in snapshot.children){
                        Log.d("database", dataModel.toString())
                        dataList.add(dataModel.getValue(memoDataModel::class.java)!!)
                    }
                    adapter_list.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                    Log.d("false", "실패")
                }

            })



    }
}