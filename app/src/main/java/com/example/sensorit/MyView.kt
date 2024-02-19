package com.example.sensorit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.max
import kotlin.math.min

class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paint = Paint()
    var points = mutableListOf<Pair<Float, Float>>()
    var cx = 200f
    var cy = 200f
    val radius = 50f

    override fun onDraw(canvas: Canvas) {
        paint.color = Color.MAGENTA
        super.onDraw(canvas)

        for (point in points) {
            canvas.drawCircle(point.first, point.second, radius, paint)
        }

    }
    fun setXY(x: Float, y: Float): Unit {
        points.add(Pair(x, y))
        shortenList(points)
        cx = max(radius, min(x, width - radius))
        cy = max(radius, min(y, height - radius))
        invalidate()
    }
    private fun<T> shortenList(pList: MutableList<T>, length:Int=8) {
        while(pList.size>length) { pList.removeAt(0)
        }
    }

}