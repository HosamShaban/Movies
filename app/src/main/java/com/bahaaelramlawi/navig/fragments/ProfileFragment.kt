package com.bahaaelramlawi.navig.fragments

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bahaaelramlawi.navig.Profile.DatabaseHelper
import com.bahaaelramlawi.navig.R
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    lateinit var sharedPreferences : SharedPreferences
    lateinit var name : String
    lateinit var mobile : String
    lateinit var pass : String

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        ////////////////////////////////////////////////////////////////////////////////////////////
        val db= DatabaseHelper(this.requireContext())
        root.imgGallery.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(gallery , 100)
        }
        root.btnSave.setOnClickListener {
            Toast.makeText(activity, "name is ${edName.text} phone is ${edPhone.text}"
                    , Toast.LENGTH_LONG).show()
            db.saveProfile(btnSave , "" ,  0, "")
        }
        ////////////////////////////////////////////////////////////////////////////////////////////

        sharedPreferences =this.requireActivity().getSharedPreferences("Login", AppCompatActivity.MODE_PRIVATE)
        name = sharedPreferences.getString("username", "null").toString()
        mobile = sharedPreferences.getString("mobile", "null").toString()
        pass = sharedPreferences.getString("password", "null").toString()
        root.edName.setText(name)
        root.edPhone.setText(mobile)
        root.edPassword.setText(pass)
        return root
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        {
            super.onActivityResult(requestCode, resultCode, data)
            if(requestCode == Activity.RESULT_OK && requestCode == 100)
                {
                    Log.e("hos" , data!!.data.toString())
                    imgGallery.setImageURI(data!!.data)
                }

        }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}