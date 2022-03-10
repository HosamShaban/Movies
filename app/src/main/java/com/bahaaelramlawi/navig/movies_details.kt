package com.example.projectlistview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bahaaelramlawi.navig.R
import com.bahaaelramlawi.navig.model.Movies
import kotlinx.android.synthetic.main.activity_movies_details.*

class movies_details : AppCompatActivity()
    {
        override fun onCreate(savedInstanceState: Bundle?)
            {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_movies_details)
                val s = intent.getParcelableExtra<Movies>("data")
                txtName.text = s!!.name
                txtYear.text = s.year
                setOf(s.info)
                image.setImageResource(s.image)
            }
    }