package com.example.jbe_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.jbe_app.data.PostModel
import kotlinx.android.synthetic.main.fragment_second.view.*

class BreweryAdapter(var listener: MainActivity) : RecyclerView.Adapter<BreweryAdapter.BreweryViewHolder>(){

    private var data : ArrayList<PostModel>?=null
    interface BreweryListener{
        fun onItemDeleted(postModel: PostModel, position: Int)
    }

    fun setData(list: ArrayList<PostModel>){
        data = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryViewHolder {
        return BreweryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: BreweryViewHolder, position: Int) {

        val item = data?.get(position)
        holder.bindView(item)
        holder.itemView.img_delete.setOnClickListener {
            item?.let { it1 ->
                listener.onItemDeleted(it1, position)
            }
        }
    }


    class BreweryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(item: PostModel?) {
            val itemTitle = itemView.item_title
            val itemBody = itemView.item_body
            //val itemTitle:TextView = itemView.findViewById(R.id.item_title)!!
            //val itemBody: TextView = itemView.findViewById(R.id.item_body)!!

            itemTitle.text = item?.name
            itemBody.text = item?.city

                    //             itemView.test_title.text = item?.name
            //            itemView.item_body.text = item?.city
        }

    }

}