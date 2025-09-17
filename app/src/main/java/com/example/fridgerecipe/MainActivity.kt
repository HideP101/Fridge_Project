package com.example.fridgerecipe

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fridgerecipe.databinding.ActivityMainBinding
import com.example.fridgerecipe.ui.viewmodel.FridgeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: FridgeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상단 칸
        binding.topDoor.setOnClickListener {
            val isOpen = viewModel.topDoorOpen.value ?: false
            animateDoor(binding.topDoor, !isOpen, pivotLeft = true)
            viewModel.topDoorOpen.value = !isOpen
        }

        // 중단 칸
        binding.middleDoor.setOnClickListener {
            val isOpen = viewModel.middleDoorOpen.value ?: false
            animateDoor(binding.middleDoor, !isOpen, pivotLeft = true)
            viewModel.middleDoorOpen.value = !isOpen
        }

        // 하단 칸 (내부 화면 보여주기)
        binding.bottomDoor.setOnClickListener {
            binding.fridgeInside.visibility =
                if (binding.fridgeInside.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }

    private fun animateDoor(view: View, open: Boolean, pivotLeft: Boolean) {
        view.pivotY = view.height / 2f
        view.pivotX = if (pivotLeft) 0f else view.width.toFloat()

        val start = if (open) 0f else -90f
        val end = if (open) -90f else 0f

        ObjectAnimator.ofFloat(view, "rotationY", start, end).apply {
            duration = 500
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }
}
