package com.bahaaelramlawi.navig.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bahaaelramlawi.navig.R
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingFragment : Fragment()
    {


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            {
                val root = inflater.inflate(R.layout.fragment_setting, container, false)

                ////////////////////////////////////////////////////////
                //txtShare used to share the application
                root.txtShare.setOnClickListener {
                    var intent= Intent(Intent.ACTION_SEND)
                    intent.setType("text/plain")
                    intent.putExtra(Intent.EXTRA_TEXT,txtOther.text.toString())
                    startActivity(intent)
                }
                ////////////////////////////////////////////////////////


                return root
            }
    }