package com.example.projectlistview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahaaelramlawi.navig.R
import com.bahaaelramlawi.navig.model.Movies
import kotlinx.android.synthetic.main.movie_item.view.*

class RecyclerList_Adapter(val context: Context, val list: List<Movies>, var Click: onClick): RecyclerView.Adapter<RecyclerList_Adapter.ViewHolder>() {

    class ViewHolder(item:View):RecyclerView.ViewHolder(item){

        //The variables to be displayed in the list
        var image = item.movie_image
        var name = item.txt_name
        var year = item.txt_year
        var info = item.txt_info

        ///////////////////////////////////////////
        var card=item.card_item
        var saved:Boolean = false
        ///////////////////////////////////////////
    }

    //Used to take items from a user and mount them to a list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false)
        return ViewHolder(view)
    }

    //Used to take items from a user and mount them to a list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text=list[position].name
        holder.year.text=list[position].year
        holder.info.text=list[position].info
        holder.image.setImageResource(list[position].image)
        holder.saved = false




        holder.card.setOnClickListener{
            Click.onClickItem(holder.adapterPosition)
        }

    }



    override fun getItemCount(): Int {
        return list.size
    }

    /////////////////////////////////////////////
    // Use ClickListener For RecyclerView.
    interface onClick{
      fun onClickItem(position: Int)
    }
    ///////////////////////////////////////////
}