package com.azurgame.blck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.azurgame.R
import com.azurgame.blck.STAN.C1
import com.azurgame.blck.STAN.D1
import com.azurgame.blck.STAN.DEV
import com.azurgame.game.NumberMainScreen
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_filt.*
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class Filt : AppCompatActivity() {
    lateinit var jsoup: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filt)

        jsoup = ""

        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = coroutineTask()
            Log.d("jsoup status from global scope", jsoup)
        }

        runBlocking {
            try {
                job.join()

                Log.d("jsoup status out of global scope", jsoup)
                txtMain.text = jsoup

                if (jsoup == STAN.jsoupCheck) {
                    Intent(applicationContext, NumberMainScreen::class.java).also { startActivity(it) }
                } else {
                    Intent(applicationContext, Webs::class.java).also { startActivity(it) }
                }
                finish()
            } catch (e: Exception) {

            }
        }

    }

    private suspend fun coroutineTask(): String {
        val hawk: String? = Hawk.get(C1, "null")
        val hawkAppLink: String? = Hawk.get(D1, "null")
        val hawkDevOrNot: String? = Hawk.get(DEV, "false")


        //added devModeCheck
        val forJsoupSetNaming: String = STAN.lru + STAN.odone + hawk + "&" + STAN.twoSub + hawkDevOrNot
        val forJsoupSetAppLnk: String = STAN.lru + STAN.odone + hawkAppLink + "&" +  STAN.twoSub + hawkDevOrNot

        withContext(Dispatchers.IO) {
            //changed logical null to string null
            if (hawk != "null") {
                getCodeFromUrl(forJsoupSetNaming)
                Log.d("Check1C", forJsoupSetNaming)
            } else {
                getCodeFromUrl(forJsoupSetAppLnk)
                Log.d("Check1C", forJsoupSetAppLnk)
            }
        }
        return jsoup
    }

    private fun getCodeFromUrl(link: String) {
        val url = URL(link)
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            val text = urlConnection.inputStream.bufferedReader().readText()
            if (text.isNotEmpty()) {
                Log.d("jsoup status inside Url function", text)
                jsoup = text
            } else {
                Log.d("jsoup status inside Url function", "is null")
            }
        } catch (ex: Exception) {

        } finally {
            urlConnection.disconnect()
        }
    }
}