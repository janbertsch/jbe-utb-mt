package com.example.jbe_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.jbe_app.data.AppDatabase
import com.example.jbe_app.data.PostModel
import kotlinx.android.synthetic.main.item_view.view.*

class BreweryAdapter(var listener: FirstFragment, private val db: AppDatabase) : RecyclerView.Adapter<BreweryAdapter.BreweryViewHolder>(){

    private var data : List<PostModel>?=null
    interface BreweryListener{
        fun onItemDeleted(postModel: PostModel, position: Int)
    }

    fun setData(list: List<PostModel>){
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

        holder.itemView.img_favourite.setOnClickListener {
            item?.let { postModel ->
                // Insert the item into the database in a background thread
                Thread {
                    db.postModelDao().insertAll(postModel)
                }.start()

                Log.i("tag", "Saved to favorites: ${postModel.name}")
            }
        }
    }

    class BreweryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(item: PostModel?) {
            val itemTitle = itemView.item_title
            val itemBody = itemView.item_body
            //val itemTitle:TextView = itemView.findViewById(R.id.item_title)
            //val itemBody: TextView = itemView.findViewById(R.id.item_body)

            itemTitle.text = item?.name
            itemBody.text = item?.city
        }

    }

}