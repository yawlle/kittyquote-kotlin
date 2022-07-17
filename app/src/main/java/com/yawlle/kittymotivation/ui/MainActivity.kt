package com.yawlle.kittymotivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yawlle.kittymotivation.infra.MotivationConstants
import com.yawlle.kittymotivation.R
import com.yawlle.kittymotivation.infra.SecurityPreferences
import com.yawlle.kittymotivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNewPhrase.setOnClickListener(this)

        supportActionBar?.hide()

        handleUserName()


    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_new_phrase){
            var s = ""
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.helloName.text = "Ola, $name!"
    }
}