package com.conan.android.hencoderplus6

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin


private val String.color
    get() = Color.parseColor(this)

private val RADIUS = 130f.px
private val OFFSET_LENGTH = 20f.px
private val ANGLES = floatArrayOf(60f, 90f, 150f, 60f)
private val COLORS = listOf("#2879FD".color, "#C1175C".color, "#009688".color, "#FF8E00".color)

class Preview(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun onDraw(canvas: Canvas) {

        var startAngle = 0f
        for ((index, angle) in ANGLES.withIndex()) {

            if (index == 2) {
                canvas.save()
                canvas.translate(OFFSET_LENGTH * cos(Math.toRadians(startAngle + angle / 2.0)).toFloat(), OFFSET_LENGTH * sin(Math.toRadians(startAngle + angle / 2.0)).toFloat());
            }

            paint.color = COLORS[index]
            canvas.drawArc(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS, height / 2f + RADIUS, startAngle, angle, true, paint)
            startAngle += angle

            if (index == 2) {
                canvas.restore()
            }
        }
    }

}