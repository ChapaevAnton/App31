package com.w4eret1ckrtb1tch.app31

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class ClockView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) :
    View(context, attributeSet) {

    var centerX = 0f
    var centerY = 0f
    var radius = 0f
    var scaleSize = 60f

    private var isStaticPictureDraw: Boolean = false
    private lateinit var bitmap: Bitmap
    private lateinit var staticCanvas: Canvas

    private val dashColor = Color.BLUE
    private val digitColor = Color.WHITE
    private val arrowColor = Color.RED
    private val circleColor = Color.BLACK

    private lateinit var dashPaint: Paint
    private lateinit var clockPaint: Paint
    private lateinit var circleClockPaint: Paint

    private val rect = Rect()

    private val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)


    init {
        initDrawingTools()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthSize = MeasureSpec.getSize(widthMeasureSpec) + paddingStart + paddingEnd
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec) + paddingTop + paddingBottom
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)


        val chooseWidth = chooseDimension(widthMode, widthSize)
        val chooseHeight = chooseDimension(heightMode, heightSize)

        val minSide = min(chooseWidth, chooseHeight)

        centerX = minSide.div(2f)
        centerY = minSide.div(2f)

        setMeasuredDimension(minSide, minSide)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val minSide = min(width, height)
        radius = minSide.div(2f)
    }


    override fun onDraw(canvas: Canvas?) {
        if (!isStaticPictureDraw) {
            drawStaticPicture()
        }

        canvas?.drawBitmap(bitmap, centerX - radius, centerY - radius, null)
    }

    private fun drawStaticPicture() {

        bitmap = Bitmap.createBitmap(
            (centerX * 2).toInt(),
            (centerY * 2).toInt(),
            Bitmap.Config.ARGB_8888
        )
        staticCanvas = Canvas(bitmap)

        drawClock(staticCanvas)

        isStaticPictureDraw = true

    }

    private fun drawClock(canvas: Canvas) {
        canvas.translate(centerX, centerY)

        canvas.drawCircle(0f, 0f, radius, circleClockPaint)

        for (number in numbers) {

            val text = number.toString()
            val digitOfSet = 0.9f
            clockPaint.getTextBounds(text, 0, text.length, rect)


            val angel = PI / 6 * (number - 3)

            val x = (cos(angel) * radius * digitOfSet - rect.width() / 2).toFloat()
            val y = (sin(angel) * radius * digitOfSet + rect.height() / 2).toFloat()

            canvas.drawText(text, x, y, clockPaint)
        }

    }

    private fun chooseDimension(mode: Int, size: Int): Int {
        return when (mode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> size
            else -> 300
        }
    }

    private fun initDrawingTools() {

        dashPaint = Paint().apply {
            color = dashColor
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = 0.01f
            isAntiAlias = true
        }
        clockPaint = Paint(dashPaint).apply {
            strokeWidth = 2f
            textSize = scaleSize * 1.5f
            color = digitColor
            isAntiAlias = true
        }
        circleClockPaint = Paint().apply {
            color = circleColor
            style = Paint.Style.FILL
            strokeWidth = 10f
            isAntiAlias = true
        }
    }


}