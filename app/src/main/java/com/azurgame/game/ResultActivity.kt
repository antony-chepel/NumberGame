package com.azurgame.game
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.azurgame.R
import com.azurgame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRes()
    }

    private fun getRes() = with(binding){
       val sadres = intent.getStringExtra("sad")
       val success = intent.getStringExtra("smile")

        if(sadres?.isNotEmpty() == true) {
            imSmile.setImageResource(R.drawable.ssile_sad)
            tvText.text = "Unluck"
        }

        if(success?.isNotEmpty() == true){
            imSmile.setImageResource(R.drawable.smile_happy)
        }

        bAgain.setOnClickListener {
            finish()
        }
    }
}