package com.example.opensourcefinal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opensourcefinal.ContentsModel


class mainFragment : Fragment() {

    private val items = mutableListOf<ContentsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_main, container, false)


        //구 위치만 추가???
        items.add(ContentsModel("서초구 맛집"))
        items.add(ContentsModel("광진구 맛집"))
        items.add(ContentsModel("용산구 맛집"))

        //리사이클러뷰 연결
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv)
        //리사이클러뷰 가져온 후 리사이클러뷰의 어댑터에 연결해
        val rvAdapter = MainRvAdapter(items)
        recyclerView.adapter = rvAdapter

        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.layoutManager = GridLayoutManager(this, 2)
        //이렇게 하면 한줄에 2개 추가하는거야

        //이제 해당 아이템 클릭이벤트 추가ㅏ(MainFragment에 있는 아이템)
        rvAdapter.itemClick = object : MainRvAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                //아이템 클릭이 되면 새로운 액티비티로 넘겨줘 --> API를 적용한 리사이클러뷰가 있는 곳?
                //mainFragment에서 클릭시...
                val intent = Intent(context, APIActivity::class.java)
                startActivity(intent)
            }

        }


        //2번화면에서는 1번 화면 3번 화면으로 넘어가는 클릭 이벤트만 필요!
        val btn1 = view.findViewById<TextView>(R.id.btn1)
        btn1.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_dateFragment)
        }

        val btn3 = view.findViewById<TextView>(R.id.btn3)
        btn3.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_memoFragment)
        }

        return view
    }



}