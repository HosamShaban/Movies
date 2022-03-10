package com.bahaaelramlawi.navig.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bahaaelramlawi.navig.DetailsActivity
import com.bahaaelramlawi.navig.R
import com.bahaaelramlawi.navig.model.Movies
import com.example.projectlistview.adapter.RecyclerList_Adapter
import com.example.projectlistview.db.MoviesDataBaseHandler
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment(),RecyclerList_Adapter.onClick
    {
        lateinit var saved_list: List<Movies>

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            {

                val root = inflater.inflate(R.layout.fragment_favorite, container, false)
                var db: MoviesDataBaseHandler = MoviesDataBaseHandler(this.requireActivity(), "favorite")
                saved_list = db.getMovies()

                var adapter= RecyclerList_Adapter(this.requireActivity(), saved_list, this)
                root.rv_saved_items.layoutManager= LinearLayoutManager(this.requireActivity(), LinearLayoutManager.VERTICAL,false)
                root.rv_saved_items.adapter=adapter
                return root
            }

        ////////////////////////////////////////////////////////////////////////////////////////////////

        //To move from FavoriteFragment to DetailsActivity & Data transfer.
        override fun onClickItem(position: Int)
            {
                var intent= Intent(this.requireActivity(), DetailsActivity::class.java)
                intent.putExtra("title",saved_list[position].name)
                intent.putExtra("year",saved_list[position].year)
                intent.putExtra("info",saved_list[position].info)
                intent.putExtra("image",saved_list[position].image)
                this.requireActivity().startActivity(intent)
            }

        ////////////////////////////////////////////////////////////////////////////////////////////////
    }
