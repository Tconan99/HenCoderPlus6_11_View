package com.conan.android.hencoderplus6

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

private const val OPEN_ANGLE = 120f
private val RADIUS = 120f.px
private val LENGTH = 80f.px
private val DASH_WIDTH = 2f.px
private val DASH_LENGTH = 10f.px

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3f.px
        style = Paint.Style.STROKE
    }

    private val dashPath = Path().apply {
        addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
    }

    private val path = Path()
    private lateinit var pathEffect: PathDashPathEffect

    private var progress: Float = 25f
        set(value) {
            field = value
            invalidate()
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(
            width / 2f - RADIUS, height / 2f - RADIUS,
            width / 2f + RADIUS, height / 2f + RADIUS,
            90f + OPEN_ANGLE / 2f, 360f - OPEN_ANGLE
        )

        val pathMeasure = PathMeasure(path, false)
        pathMeasure.length / 20f
        pathEffect = PathDashPathEffect(
            dashPath,
            (pathMeasure.length - DASH_WIDTH) / 20f,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        paint.pathEffect = null
        canvas.drawPath(path, paint)

        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)

        val radians = Math.toRadians(90.0 + OPEN_ANGLE / 2f + (360f - OPEN_ANGLE) * progress / 100f)
        val x = width / 2f + LENGTH * cos(radians).toFloat()
        val y = height / 2f + LENGTH * sin(radians).toFloat()
        canvas.drawLine(width / 2f, height / 2f, x, y, paint)

//        if (progress < 100f) {
//            progress += 0.1f
//        } else {
//            progress = 0f
//        }
    }

}