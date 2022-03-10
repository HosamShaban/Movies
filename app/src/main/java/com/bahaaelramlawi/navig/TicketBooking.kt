package com.example.projectlistview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bahaaelramlawi.navig.R
import com.bahaaelramlawi.navig.model.Movies
import com.example.projectlistview.adapter.RecyclerList_Adapter
import com.example.projectlistview.db.MoviesDataBaseHandler
import kotlinx.android.synthetic.main.activity_ticket_booking.*


class TicketBooking : AppCompatActivity(),RecyclerList_Adapter.onClick
    {
        lateinit var saved_list: List<Movies>
        override fun onCreate(savedInstanceState: Bundle?)
            {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_ticket_booking)

                var db: MoviesDataBaseHandler = MoviesDataBaseHandler(this, "booking")
                saved_list = db.getMovies()

                var adapter= RecyclerList_Adapter(this, saved_list, this)
                rv_saved_items.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                rv_saved_items.adapter=adapter
            }

        override fun onClickItem(position: Int)
            {

            }
    }