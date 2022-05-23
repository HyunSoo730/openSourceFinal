package com.example.opensourcefinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opensourcefinal.ContentsModel

//이미지와 텍스트 링크까지 받아올꺼라 데이터모델형태로 받아오자
class MainRvAdapter(val List : MutableList<ContentsModel>) : RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRvAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent, false)
        return ViewHolder(v)
    }

    interface ItemClick{
        fun onClick(view : View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: MainRvAdapter.ViewHolder, position: Int) {

        if(itemClick != null){
            holder?.itemView.setOnClickListener{v->
                itemClick!!.onClick(v,position)

            }
        }

        holder.bindItems(List[position])
    }

    override fun getItemCount(): Int {
        return List.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : ContentsModel){   //바인딩해줘야해
            //Main에서 추가한 내용 여기서 넘겨받아 연결시켜줘야지
            //지금은 일단 이미지 안할꺼니깐 text만 연결
            val rv_text = itemView.findViewById<TextView>(R.id.rvTextArea)
            rv_text.text = item.name
        }
    }
}