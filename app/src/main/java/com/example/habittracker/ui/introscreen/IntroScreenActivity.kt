package com.example.habittracker.ui.introscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.habittracker.R
import com.example.habittracker.data.model.IntroView
import com.example.habittracker.ui.MainActivity
import kotlinx.android.synthetic.main.activity_intro_screen.*

class IntroScreenActivity : AppCompatActivity() {

    lateinit var introView: List<IntroView>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_screen)
        addToIntroView()

        viewPager2.adapter = ViewPagerIntroAdapter(introView)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        circleIndicator.setViewPager(viewPager2)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 2) {
                    animationButton()
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    private fun animationButton() {
        btn_start_app.visibility = View.VISIBLE

        btn_start_app.animate().apply {
            duration = 1400
            alpha(1f)

            btn_start_app.setOnClickListener {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }

    private fun addToIntroView() {

        introView = listOf(
            IntroView("Welcome to Habit Tracker!", R.drawable.ic_tea),
            IntroView(
                "This app is designed to keep track of your habits, " + "whether it's a good one, or a bad one.",
                R.drawable.ic_baseline_fastfood_24
            ),
            IntroView(
                "Good luck! Tap on the button below to get started with using the app!",
                R.drawable.ic_smoking2
            )
        )
    }
}