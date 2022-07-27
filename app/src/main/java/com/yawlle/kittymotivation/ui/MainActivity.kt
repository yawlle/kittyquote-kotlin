package com.yawlle.kittymotivation.ui

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.yawlle.kittymotivation.R
import com.yawlle.kittymotivation.databinding.ActivityMainBinding
import com.yawlle.kittymotivation.infra.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.COMPUTER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageComputer.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.helloName.setOnClickListener(this)


        supportActionBar?.hide()

        handleUserName()
        handleFilter(R.id.image_computer)
        handleNextPhrase(categoryId)


    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_new_phrase -> {
                handleNextPhrase(categoryId)
            }
            in listOf(R.id.image_sunny, R.id.image_computer, R.id.image_happy) -> {
                handleFilter(view.id)
            }
            R.id.hello_name -> {
                startActivity(Intent(this, UserActivity::class.java))
            }
        }

    }

    private fun handleNextPhrase(categoryId: Int) {

        when (categoryId) {
            MotivationConstants.FILTER.COMPUTER -> {
                requirePhraseProgramming()
            }
            MotivationConstants.FILTER.ANIME -> {
                requirePhraseAnime()
            }
            MotivationConstants.FILTER.CODE -> {
                requirePhraseDune()
            }
        }


    }

    private fun requirePhraseProgramming() {
        val retrofitInitializer = RetrofitInitializerProgramming.create().getPhrase()
        retrofitInitializer.enqueue(object : Callback<Phrase> {
            override fun onResponse(call: Call<Phrase>, response: Response<Phrase>) {
                binding.textViewPhrase.text = response.body().toString()
                binding.textViewPhrase.movementMethod = ScrollingMovementMethod()
            }

            override fun onFailure(call: Call<Phrase>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun requirePhraseAnime() {
        val retrofitInitializer = RetrofitInitializerAnime.create().getPhraseAnime()
        retrofitInitializer.enqueue(object : Callback<PhraseAnime> {
            override fun onResponse(call: Call<PhraseAnime>, response: Response<PhraseAnime>) {
                binding.textViewPhrase.text = response.body().toString()
                binding.textViewPhrase.movementMethod = ScrollingMovementMethod()
            }

            override fun onFailure(call: Call<PhraseAnime>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun requirePhraseDune() {
        val retrofitInitializer = RetrofitInitializerCode.create().getPhraseDune()
        retrofitInitializer.enqueue(object : Callback<PhraseCode> {
            override fun onResponse(call: Call<PhraseCode>, response: Response<PhraseCode>) {
                binding.textViewPhrase.text = response.body().toString()
                binding.textViewPhrase.movementMethod = ScrollingMovementMethod()
            }

            override fun onFailure(call: Call<PhraseCode>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        "Hi, $name!".also { binding.helloName.text = it }
    }

    private fun handleFilter(id: Int) {

        binding.imageComputer.setColorFilter(ContextCompat.getColor(this, R.color.bold_color))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.bold_color))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.bold_color))

        when (id) {
            R.id.image_computer -> {
                binding.imageComputer.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.COMPUTER
                handleNextPhrase(categoryId)
                binding.linearMain.setBackgroundColor(ContextCompat.getColor(this,R.color.programming_background_color))
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ANIME
                handleNextPhrase(categoryId)
                binding.linearMain.setBackgroundColor(ContextCompat.getColor(this,R.color.anime_background_color))
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.CODE
                handleNextPhrase(categoryId)
                binding.linearMain.setBackgroundColor(ContextCompat.getColor(this,R.color.code_background_color))
            }
        }

    }
}

