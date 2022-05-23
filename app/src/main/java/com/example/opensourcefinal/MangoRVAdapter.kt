package com.example.opensourcefinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.opensourcefinal.MangoModel
import com.example.opensourcefinal.R

//mango웹뷰 rv리스트를 받아올 어댑터
class MangoRVAdapter(val context : Context, val List : MutableList<MangoModel>) : RecyclerView.Adapter<MangoRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangoRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.mango_rv_item,parent,false)

        return ViewHolder(v)
    }

    interface ItemClick{
        fun onClick(view : View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: MangoRVAdapter.ViewHolder, position: Int) {
        if(itemClick != null){
            holder?.itemView.setOnClickListener {v->
                itemClick!!.onClick(v,position)
            }
        }   //아이템 클릭 이벤트
        holder.binditems(List[position])
    }

    override fun getItemCount(): Int {
        return List.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun binditems(item : MangoModel){
            val rv_img = itemView.findViewById<ImageView>(R.id.rvImageArea)
            val rv_text = itemView.findViewById<TextView>(R.id.rvTextArea)

            rv_text.text = item.titleText
            Glide.with(context)
                .load(item.ImageUrl).into(rv_img)
        }
    }


}