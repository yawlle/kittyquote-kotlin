package com.yawlle.kittymotivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.yawlle.kittymotivation.R
import com.yawlle.kittymotivation.databinding.ActivityMainBinding
import com.yawlle.kittymotivation.infra.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)


        supportActionBar?.hide()

        handleUserName()
        handleFilter(R.id.image_all)
        handleNextPhrase()


    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_happy, R.id.image_all, R.id.image_sunny)) {
            handleFilter(view.id)
        }

    }

    private fun handleNextPhrase() {
        requirePhrase()
    }

    private fun requirePhrase() {
        val retrofitInitializer =  RetrofitInitializer.create().getPhrase()
        retrofitInitializer.enqueue(object : retrofit2.Callback<Phrase> {
            override fun onResponse(call: Call<Phrase>, response: Response<Phrase>) {
                    binding.textViewPhrase.text = response.body().toString()

            }

            override fun onFailure(call: Call<Phrase>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        "Ola, $name!".also { binding.helloName.text = it }
    }

    private fun handleFilter(id: Int) {

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.bold_color))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.bold_color))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.bold_color))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
        }

    }
}

