package com.example.appclasse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import com.example.appclasse.databinding.ActivityDragAndDropBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlin.reflect.safeCast

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
                true
            }

            binding.chipGroup2.addView(chip)
        }

        binding.chipGroup.setOnDragListener(dragListener)

        binding.blackhole.alpha = 0f
        binding.blackhole.setOnDragListener(removeDragListener)

    }

    val dragListener = View.OnDragListener { v, event ->

        val movingChip = Chip::class.safeCast(event.localState) ?: return@OnDragListener false

        when(event.action)
        {
            DragEvent.ACTION_DRAG_STARTED -> {
                movingChip.setTextColor(getColor(R.color.transparent))
                movingChip.setChipStrokeColorResource(R.color.black)
                movingChip.chipStrokeWidth = 4f
               // movingChip.setBackgroundResource(R.color.transparent)
            }


            DragEvent.ACTION_DROP -> {
                val parent = ChipGroup::class.safeCast(movingChip.parent)
                parent?.removeView(movingChip)

                ChipGroup::class.safeCast(v)?.addView(movingChip)
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                movingChip.setTextColor(getColor(R.color.black))
                movingChip.chipStrokeWidth = 0f
               // movingChip.setBackgroundResource(R.color.transparent)
            }
        }

        true
    }

    val removeDragListener = View.OnDragListener { v, event ->

        val movingChip = Chip::class.safeCast(event.localState) ?: return@OnDragListener false

        when(event.action)
        {
            DragEvent.ACTION_DRAG_STARTED -> {
                binding.blackhole.animate().alpha(1f)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                binding.blackhole.animate().scaleX(1.6f).scaleY(1.6f)

            }

            DragEvent.ACTION_DRAG_EXITED -> {
                binding.blackhole.animate().scaleX(1f).scaleY(1f)
            }

            DragEvent.ACTION_DROP -> {
                val parent = ChipGroup::class.safeCast(movingChip.parent)
                parent?.removeView(movingChip)
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                binding.blackhole.animate().alpha(0f)


            }
        }

        true
    }

}