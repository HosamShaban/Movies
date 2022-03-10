package com.bahaaelramlawi.navig

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bahaaelramlawi.navig.model.Movies
import com.example.projectlistview.adapter.RecyclerList_Adapter
import com.example.projectlistview.db.MoviesDatabaseHelper
import kotlinx.android.synthetic.main.movies_activity_main.*

class MoviesMainActivity : AppCompatActivity(),RecyclerList_Adapter.onClick {

    var data = ArrayList<Movies>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_activity_main)

        val db= MoviesDatabaseHelper(this)
      db.getAllstudents()
        data.add(Movies("Tenet" , "Year: 2020" , "Action | Fiction" , R.drawable.b1))
        data.add(Movies("Lost Bullet" , "Year: 2020" , "Action | Excitement | Crime" , R.drawable.b2))
        data.add(Movies("Demon Slayer" , "Year: 2020" , "Shounen | Animation" , R.drawable.b3))
        data.add(Movies("Mosul" , "Year: 2020" , "Action | War" , R.drawable.a9))
        data.add(Movies("The Dark Knight" , "Year:2008 " , "Action | Drama | Crime" , R.drawable.b4))
        data.add(Movies("Django Unchained " , "Year: 2012" , "Action | Drama" , R.drawable.b5))
        data.add(Movies("The Hitman Bodyguard" , "Year: 2017" , "Action | Excitement" , R.drawable.b6))
        data.add(Movies("John Wick" , "Year: 2014" , "Action | Excitement" , R.drawable.b7))
        data.add(Movies("Red" , "Year: 2012" , "Action | Adventure" , R.drawable.a1))
        data.add(Movies("Expendables" , "Year: 2009" , "Action | Adventure",R.drawable.a2))
        data.add(Movies("Black hak down" , "Year: 2001" , "Action | Adventure | warm",R.drawable.a3))
        data.add(Movies("Hit man" , "Year: 2015" , "Action | Adventure" , R.drawable.a4))
        data.add(Movies("Extraction" , "Year: 2020" , "Action | Adventure" , R.drawable.a5))
        data.add(Movies("Titanic" , "Year: 1997" , "Drama| Adventure | Romantic" ,R.drawable.a6))
        data.add(Movies("Home Alone" , "Year: 2000" , "Comedy | Family" , R.drawable.a7))
        data.add(Movies("Shooter" , "Year: 2007" , "Action | Adventure" , R.drawable.a8))


      var adapter= RecyclerList_Adapter(this, data,this)//-------------
      RvMovies.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)//-----
      RvMovies.adapter=adapter

    }

  //---------------------------------------------
  override fun onClickItem(position: Int) {
    var intent=Intent(this, DetailsActivity::class.java)
    intent.putExtra("title",data[position].name)
    intent.putExtra("year",data[position].year)
    intent.putExtra("info",data[position].info)
    intent.putExtra("image",data[position].image)
    startActivity(intent)
  }


}