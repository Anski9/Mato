package com.example.sensorit

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() , SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var myView: MyView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        myView = findViewById(R.id.myView)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val screenWidth = 1080f
            val screenHeight = 2150f

            val sensorX = max(-10f, min(10f, -it.values[0]))
            val sensorY = max(-10f, min(10f, it.values[1]))

            val scaledX = ((sensorX + 10) / 20) * screenWidth
            val scaledY = ((sensorY + 10) / 20) * screenHeight

            myView.setXY(scaledX, scaledY)

            Log.d("SensorDebug", "X: ${it.values[0]}, Y: ${it.values[1]}, Z: ${it.values[2]}")
        }

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}