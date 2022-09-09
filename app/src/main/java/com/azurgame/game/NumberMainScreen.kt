package com.azurgame.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.azurgame.databinding.ActivityNumberMainScreenBinding

class NumberMainScreen : AppCompatActivity() {
    private lateinit var binding : ActivityNumberMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dfsdfsd()
    }


    private fun dfsdfsd() = with(binding){
        bContinue.setOnClickListener {
            val nameText = edName.text.toString()

            if(nameText.isEmpty() || nameText.length<3){
                tvError.visibility = View.VISIBLE
            } else {
                startActivity(
                    Intent(this@NumberMainScreen, NumberGame::class.java)
                    .putExtra("name",nameText)
                )
                tvError.visibility = View.GONE
                edName.text.clear()
                edName.clearFocus()
            }

        }
    }
}