package com.bahaaelramlawi.navig

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
        {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)

            ////////////////////////////////////////////////////////////////////////

            // Handler().postDelayed used for show Splash in start
            Handler ().postDelayed(
                    {
                        startActivity(Intent(this, LoginActivity :: class.java))
                        finish()
                    }, 2000) //Splash display duration ---> delayMillis:2000 = 2 second.

            /////////////////////////////////////////////////////////////////////////////
        }
}