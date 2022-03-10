package com.bahaaelramlawi.navig

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bahaaelramlawi.navig.db.DatabaseHandler
import com.bahaaelramlawi.navig.model.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    var mobile: String = ""
    var password: String = ""
    var db: DatabaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?)
        {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            ////////////////////////////////////////////////////////////////////
            sharedPreferences =getSharedPreferences("Login", MODE_PRIVATE)
            if(sharedPreferences.getBoolean("isLogin", false))
                {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            ///////////////////////////////////////////////////////////////////////

            txv_register.setOnClickListener {
                startActivity(Intent(this , RegisterActivity :: class.java))
            }
            btnSign.setOnClickListener {
                mobile = txv_mobile.text.toString()
                password = txtPassword1.text.toString()
                login_request(mobile, password)
            }
            ///////////////////////////////////////////////////////////////////////////////////
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Function used for Check data for Login.
    private fun login_request(mobile_num:String, password:String)
        {
            //A Boolean variable was specified to see if the user data matched or not.
            var ready:Boolean = true

            //Checking the length of the mobile number must be less than 10 digits
            if(mobile_num.length<10)
                {
                    Toast.makeText(this, "Enter correct mobile num !!", Toast.LENGTH_SHORT).show()
                    ready = false
                }

            //Checking the length of the password must be less than 5 digits
            if(password.length<5)
                {
                    Toast.makeText(this, "Password should be 5-digits at min !!", Toast.LENGTH_SHORT).show()
                    ready = false
                }

            //If the user data are correct and match, then the user will be entered successfully
            if(ready)
                {
                    login(mobile_num, password)
                }
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////


    private fun login (mobile_num:String, password:String)
        {
            var user_exist :Boolean = false
            var userlist:List<User> = ArrayList()
            userlist = db.viewuser()

            for(user in userlist)
                {
                    if(user.mobile.equals(mobile_num))
                        {
                            user_exist = true
                            if(user.password.equals(password))
                                {
                                    //////////////////////////////////////////////////////////
                                    val editor =sharedPreferences.edit()
                                    editor.putBoolean("isLogin",true).apply()
                                    editor.putString("username", user.username).apply()
                                    editor.putString("mobile", user.mobile).apply()
                                    editor.putString("password", user.password).apply()
                                    //////////////////////////////////////////////////////////
                                    Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this , MainActivity :: class.java))
                                }
                            else
                                {
                                    Toast.makeText(this, "Incorrect password!!", Toast.LENGTH_SHORT).show()
                                }
                        }
                }
            if(!user_exist)
                {
                    Toast.makeText(this, "Invalid user !!", Toast.LENGTH_SHORT).show()
                }
        }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}