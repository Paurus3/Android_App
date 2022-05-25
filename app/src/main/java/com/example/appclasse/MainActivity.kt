package com.example.appclasse

import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.text.TextUtils
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import com.example.appclasse.databinding.ActivityMainBinding
import java.util.*

public class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    internal var timeRunning = false
    internal lateinit var countDownTimer: CountDownTimer
    internal var initialCountDown: Long = 60000
    internal var countDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.MainButton.setOnClickListener{

        }

        binding.Timer.setOnClickListener {
            startStop();
        }

       binding.menuMainActivity.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.shop -> startActivity(Intent(this, ShopActivity::class.java))
                R.id.action_settings -> startActivity(Intent(this, SettingActivity::class.java))
                R.id.action_logout -> logout()
                else -> return@setOnMenuItemClickListener false
            }
            true
        }


    }



    //Vamos a la activity de login al hacer logout
    fun logout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun startStop()
    {
        if (timeRunning)
        {
            stopTimer()
        }else
        {
            startTimer()
        }
    }

    fun startTimer()
    {
        val initialTimeLeft = initialCountDown / 1000
        binding.Timer.text = initialTimeLeft.toString()

        countDownTimer = object: CountDownTimer(initialCountDown, countDownInterval)
        {
            override  fun onTick(millisUntilFinished: Long){
                val timeLeft = millisUntilFinished / 1000
                binding.Timer.text = timeLeft.toString()
            }
            override fun onFinish(){

            }
        }
        timeRunning = true
    }

    fun stopTimer()
    {

    }


}

