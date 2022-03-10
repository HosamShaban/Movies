package com.bahaaelramlawi.navig.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bahaaelramlawi.navig.DetailsActivity
import com.bahaaelramlawi.navig.R
import com.bahaaelramlawi.navig.model.Movies
import com.example.projectlistview.adapter.RecyclerList_Adapter
import com.example.projectlistview.db.MoviesDatabaseHelper
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(),RecyclerList_Adapter.onClick {

    var data = ArrayList<Movies>()
    var search_array = ArrayList<Movies>() // For Search

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val db= MoviesDatabaseHelper(this.requireActivity())
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

        ///////////
        var adapter= RecyclerList_Adapter(this.requireActivity(), data,this)
        root.RvMovies.layoutManager= LinearLayoutManager(this.requireActivity(), LinearLayoutManager.VERTICAL,false)
        root.RvMovies.adapter=adapter
        ///////////



        val click : RecyclerList_Adapter.onClick = this
        val context : Context = this.requireContext()

        ////////////////////////////////////////////////////////////////////////////////////////////


        //Search part *************************

        root.et_search.addTextChangedListener(object : TextWatcher
            {
                //////////////////////////////////////////

                //Function After modifying the text
                override fun afterTextChanged(s: Editable?)
                    {

                    }
                //Function Before modifying the text
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
                    {

                    }
                ////////////////////////////////////////////

                //Function While modifying the text
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
                    {
                        if(count > 0)
                            {
                                search_array = ArrayList<Movies>()
                                var string: String = s.toString()

                                //forLoop for Examine the text in the list.
                                for(item in data)
                                    {
                                        //Convert text from toLowerCase.
                                        if(item.name?.toLowerCase()?.contains(string.toLowerCase())!!)
                                            {
                                                search_array.add(item)
                                            }
                                    }

                                var adapter= RecyclerList_Adapter(context, search_array,click)
                                root.RvMovies.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                                root.RvMovies.adapter=adapter
                            }
                        //If the user does not search in the list, it is displayed completely.
                        else
                            {
                                var adapter= RecyclerList_Adapter(context, data,click)
                                root.RvMovies.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                                root.RvMovies.adapter=adapter
                            }
                    }
            })
            return root

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }



    ///////////////////////////////////////////////////////////////////////////

    //To move from HomeFragment to DetailsActivity & Data transfer.
    override fun onClickItem(position: Int)
        {
            var intent= Intent(this.requireActivity(), DetailsActivity::class.java)
            intent.putExtra("title",data[position].name)
            intent.putExtra("year",data[position].year)
            intent.putExtra("info",data[position].info)
            intent.putExtra("image",data[position].image)
            this.requireActivity().startActivity(intent)
        }
    ////////////////////////////////////////////////////////////////////////////

}