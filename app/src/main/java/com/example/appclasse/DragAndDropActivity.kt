package com.example.appclasse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.appclasse.databinding.ActivityDragAndDropBinding
import com.google.android.material.chip.Chip

class DragAndDropActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDragAndDropBinding

    private val chipsLabel = listOf(
        "Blue supergiant", "Sun-Like Star", "Red Dwarf", "Brown Dwarf", "Red Giant",// 1st phase
        "Supernova", "Blackhole", "Neutron Star", "White Dwarf", // 2nd phase
        "Neutrino", "Chupa-chups", // Not star life
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDragAndDropBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chipsLabel.map{
            val chip = Chip(this)
            chip.text = it

            chip.setOnClickListener {
                val shadow = View.DragShadowBuilder(chip)
                chip.startDragAndDrop(null, shadow, chip, 0)
            }
            
            binding.chipGroup2.addView(chip)
        }



    }


}