package com.azurgame.game


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import com.azurgame.databinding.ActivityNumberGameBinding
import kotlin.random.Random

class NumberGame : AppCompatActivity() {
    private lateinit var binding : ActivityNumberGameBinding
    private var mcjchdsd = false
    private var qweqwe: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getName()

        binding.logOut.setOnClickListener {
            finish()
        }

        binding.bResNum.setOnClickListener {
            val edText = binding.edNum.text.toString()

            if(edText.isNotEmpty()){
                iweqwwqefffsd()
                binding.tvErrorNumber.visibility = View.GONE
            } else {
                binding.tvErrorNumber.visibility = View.VISIBLE
            }


        }
    }


    @SuppressLint("SetTextI18n")
    private fun getName() =with(binding){
        val name = intent.getStringExtra("name")
        tvName.text = "Hello,$name!"
    }


    private fun iweqwwqefffsd() = with(binding){
        var counter = 0
        qweqwe?.cancel()
        qweqwe = object : CountDownTimer(3000,100){
            override fun onTick(millisUntilFinished: Long) {
                counter++
                mcjchdsd = true
                bResNum.alpha = 0.7f
                edNum.isEnabled = false
                if(counter>20) counter = 0
                tvNum.text = NumbersUtils.randomnumbers[counter]
            }

            override fun onFinish() {
                nncdswd()
                mcjchdsd = false
                edNum.isEnabled = true
                bResNum.alpha = 1.0f

            }

        }.start()

    }

    private fun nncdswd() =with(binding) {
        val tipText = NumbersUtils.randomnumbers[Random.nextInt(22)]
        val ednumText = edNum.text.toString()
        tvNum.text = tipText

        if(ednumText == tvNum.text){
          startActivity(Intent(this@NumberGame, ResultActivity::class.java)
              .putExtra("smile","smile")
          )
        } else {
            startActivity(Intent(this@NumberGame, ResultActivity::class.java)
                .putExtra("sad","sad")
            )
        }
        edNum.text.clear()
        edNum.clearFocus()
        tvNum.text = "0"


    }
}