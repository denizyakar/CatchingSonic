package com.denizyakar.catchingsonic

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.denizyakar.catchingsonic.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//List of Sonic imageViews
        val SonicList = listOf(
            binding.sonic1,
            binding.sonic2,
            binding.sonic3,
            binding.sonic4,
            binding.sonic5,
            binding.sonic6,
            binding.sonic7,
            binding.sonic8,
            binding.sonic9,
            binding.sonic10,
            binding.sonic11,
            binding.sonic12)

        //For loop to assign Listener to images and increasing the score if the clicked image is visible
        for (sonic in SonicList) {
            sonic.setOnClickListener {
                // Eğer ImageView görünürse skoru artır
                if (sonic.isVisible) {
                    score++
                    binding.scoreText.text = "Score: $score"
                }
            }
        }
//First CountDownTimer for general timer
        object: CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Time: ${millisUntilFinished/1000}"
            }
            override fun onFinish() {
              //  println("End.\n")
                binding.timeText.text = "Time: 0, finished"
            }
        }.start()

        //Second CountDownTimer to determine the time of the images appearing-disappearing
        //Also wanted to test if that will work
        object: CountDownTimer(10000,500){
            override fun onTick(millisUntilFinished: Long) {

                //Random Number Generation and Control
                var RandSonic = Random.nextInt(SonicList.size-1)
                println("\n\nStarting\n")
                println("RandomNumber: ${RandSonic}")

                //Assigning random number to the list and creating an imageview variable to the matching number
                //isVisible=true on matching Sonic ImageView
                val SelectedSonic = SonicList[RandSonic]
                println("SelectedSonic: Sonic${RandSonic}")
                SelectedSonic.isVisible = true

                println("Sonic${RandSonic}is Disappearing.")

                //imageView disappearing in 0.5 seconds
                SelectedSonic.postDelayed({
                    SelectedSonic.isVisible = false
                }, 500)
                println("Disappeared\n\n")
            }

            override fun onFinish() {
                println("End.\n")
            }
        }.start()








    }





}