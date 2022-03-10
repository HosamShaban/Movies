package com.bahaaelramlawi.navig

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bahaaelramlawi.navig.db.DatabaseHandler
import com.bahaaelramlawi.navig.model.User
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var name: String = ""
    var mobile: String = ""
    var password_1: String = ""
    var password_2: String = ""
    var db: DatabaseHandler = DatabaseHandler(this)


    override fun onCreate(savedInstanceState: Bundle?)
        {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_register)

            /////////////////////////////////////////////////////////////////
            btnRgs2.setOnClickListener{
                name = txtName.text.toString()
                mobile = phoneMNum1.text.toString()
                password_1 = txtPassword2.text.toString()
                password_2 = txtPassword3.text.toString()
                register_request(name, mobile, password_1, password_2)
            }
            ///////////////////////////////////////////////////////////////////
        }

    private fun register_request(username:String, mobile_num:String, pass_1:String, pass_2:String)
        {
            //A Boolean variable was specified to see if the user data matched or not.
            var ready:Boolean = true

            //To compare the UserName, is it the same UserName that you entered?
            if(username.length<2)
                {
                    Toast.makeText(this, "Enter correct name !!", Toast.LENGTH_SHORT).show()
                    ready = false
                }

            //To compare the mobile number, is it the same number that you entered?
            if(mobile_num.length<10)
                {
                    Toast.makeText(this, "Enter correct mobile num !!", Toast.LENGTH_SHORT).show()
                    ready = false
                }

            //To compare the password, is it the same password that you entered?
            if(pass_1.length<5)
                {
                    Toast.makeText(this, "Password should be 5-digits at min !!", Toast.LENGTH_SHORT).show()
                    ready = false
                }

            //If the second password does not match the first, an alert message appears, and the operation is rejected
            if(!pass_2.equals(pass_1))
                {
                    Toast.makeText(this, "Passwords not matched !!", Toast.LENGTH_SHORT).show()
                    ready = false
                }

            //If the user data are correct and match, then the user will be entered successfully
            if(ready){
                register(username, mobile_num, pass_1)
            }
        }

    ////////////////////////////////////////////////////////////////////////////////
    //Function used for To store user data  for register.
    private fun register (username:String , mobile_num:String, password:String)
        {
            var user: User = User(username, mobile_num, password)
            db.addUser(user)

            Toast.makeText(this, "User has been saved successfully", Toast.LENGTH_SHORT).show()
            finish() //to get out of register.
        }
    /////////////////////////////////////////////////////////////////////////////////

}