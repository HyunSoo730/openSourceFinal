package com.example.opensourcefinal

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class YongRvAdapter (val items : List<YongStore>) : RecyclerView.Adapter<YongRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YongRvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.store_list_rv,parent,false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: YongRvAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
        //롱클릭을 위한
//        holder.itemView.setOnLongClickListener{true}

//        holder.itemView.setOnClickListener {
//            itemClickListener.onClick(it,position)
//
//        }
//        holder.itemView.setOnLongClickListener {
//            itemClickListener.onLongClick(it,position)
//        }
    }

    override fun getItemCount(): Int {
        Log.d("SIZE", items.size.toString())
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

       init {
           itemView.setOnClickListener {
               itemClickListener.onClick(itemView, adapterPosition)
           }
           itemView.setOnLongClickListener {
               itemClickListener.onLongClick(itemView, adapterPosition)
               return@setOnLongClickListener true
           }
       }


        fun bindItems(item : YongStore){
            val storeName = itemView.findViewById<TextView>(R.id.storeName)
            storeName.text = item.name
            val storeAddress = itemView.findViewById<TextView>(R.id.storeAddress)
            storeAddress.text = item.address1

        }
    }


    //-----리사이클러뷰 클릭 이벤트 처리------
    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
        fun onLongClick(v : View, position : Int)
//        fun onClick(position: Int)
//        fun onLongClick(position: Int)
    }


    fun setItemClickListener(onItemClickLister : OnItemClickListener){   //클릭 리스너 등록 메서드
        this.itemClickListener = onItemClickLister
    }

    private lateinit var itemClickListener : OnItemClickListener   //클릭 리스너
    //------------------------------------
}