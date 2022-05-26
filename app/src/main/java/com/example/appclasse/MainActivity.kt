package com.example.appclasse

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateFormat
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.appclasse.databinding.ActivityMainBinding
import java.util.*

public class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    internal var timeRunning = false
    internal lateinit var countDownTimer: CountDownTimer
    internal var initialCountDown: Long = 60000
    internal var countDownInterval: Long = 1000
    internal var timeLeft = 0L



    internal var score = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.MainButton.setOnClickListener{
            initialCountDown =  binding.texInputEditTextNumber.text.toString().toLong() *600000
            binding.texInputEditTextNumber.visibility = View.INVISIBLE
            startStop()
        }

        binding.Timer.setOnClickListener {
            binding.texInputEditTextNumber.visibility = View.VISIBLE
            setTimer()
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
        if(initialCountDown != 0L)
        {
            timeRunning = true
            val initialTimeLeft = initialCountDown
            binding.MainButton.text = "Stop"
            //binding.Timer.text = initialTimeLeft.toString()

            countDownTimer = object: CountDownTimer(initialCountDown, countDownInterval)
            {
                override  fun onTick(millisUntilFinished: Long){

                    timeLeft = millisUntilFinished
                    val rep = Calendar.getInstance()
                    rep.set(0,0,0,0,0,0)
                    rep.set(Calendar.MILLISECOND, timeLeft.toInt())
                    binding.Timer.text = DateFormat.format("mm:ss", rep.time)

                }
                override fun onFinish(){
                    stopTimer()
                }
            }
            countDownTimer.start()
        }
    }


    fun stopTimer()
    {

        //Sistema punts

        //1 Hora
        if(initialCountDown >= 3600000)
        {
            if (initialCountDown - timeLeft <= 0)
            {
                score += 1000;
            }
            if (initialCountDown - timeLeft in 900000..1800000)
            {
                score += 750;
            }
            if (initialCountDown - timeLeft in 1800000..2699999)
            {
                score += 500;
            }
            if (initialCountDown - timeLeft in 2700000..3599999)
            {
                score += 250;
            }
            binding.score.text = score.toString();
        }

        // 45 min
        if(initialCountDown in 2700000..3599999)
        {
            if (initialCountDown - timeLeft <= 0)
            {
                score += 750
            }
            if (initialCountDown - timeLeft in 675000..1349999)
            {
                score += 600;
            }
            if (initialCountDown - timeLeft in 1350000..2024999)
            {
                score += 300;
            }
            if (initialCountDown - timeLeft in 2025000..2699999)
            {
                score += 150;
            }
            binding.score.text = score.toString();
        }

        // 30 min
        if(initialCountDown in 1800000..2699999)
        {
            if (initialCountDown - timeLeft <= 0)
            {
                score += 500;
            }
            if (initialCountDown - timeLeft in 450000..899999)
            {
                score += 350;
            }
            if (initialCountDown - timeLeft in 900000..1349999)
            {
                score += 250;
            }
            if (initialCountDown - timeLeft in 1350000..1799999)
            {
                score += 100;
            }
            binding.score.text = score.toString();
        }

        // 15 min
        if(initialCountDown in 900000..179999)
        {
            if (initialCountDown - timeLeft <= 0)
            {
                score += 250;
            }
            if (initialCountDown - timeLeft in 225000..449999)
            {
                score += 100;
            }
            if (initialCountDown - timeLeft in 450000..674999)
            {
                score += 50;
            }
            if (initialCountDown - timeLeft in 675000..899999)
            {
                score += 25;
            }
            binding.score.text = score.toString();
        }

        // menos 15 min
        if (initialCountDown < 899999)
        {
            if (initialCountDown - timeLeft <= 0)
            {
                score += 40;
            }
            if (initialCountDown - timeLeft in initialCountDown/4*2+1..initialCountDown/4*3)
            {
                score += 20;
            }
            if (initialCountDown - timeLeft in initialCountDown/4+1..initialCountDown/4*2)
            {
                score += 10;
            }
            if (initialCountDown - timeLeft < initialCountDown/4)
            {
                score += 1;
            }
            binding.score.text = score.toString();
        }


        countDownTimer.cancel()
        timeRunning = false
        binding.MainButton.text = "Start"
        binding.Timer.text = "00:00"
    }

    fun setTimer()
    {

    }

    override fun onPause() {
        super.onPause()
        countDownTimer.cancel()
        timeRunning = false
        binding.MainButton.text = "Start"
        binding.Timer.text = "00:00"
    }


}

