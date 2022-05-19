package com.example.appclasse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import com.example.appclasse.databinding.ActivityMainBinding

public class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private TextView countdownText;
    private Button countdownButton;

    private 


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.MainButton.setOnClickListener{

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
}

