package com.w4eret1ckrtb1tch.app31

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CustomView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null) :
    View(context, attr) {

    private var strokeWidthAttr = 0f
    private var strokeColor = 0
    private var strokePaint: Paint

    init {
        val attributes = context.theme.obtainStyledAttributes(attr, R.styleable.CustomView, 0, 0)
        try {
            strokeWidthAttr = attributes.getFloat(R.styleable.CustomView_stroke_width, 5f)
            strokeColor = attributes.getColor(R.styleable.CustomView_stroke_color, Color.BLACK)
        } finally {
            attributes.recycle()
        }

        strokePaint = Paint().apply {
            color = strokeColor
            style = Paint.Style.STROKE
            flags = Paint.ANTI_ALIAS_FLAG
            strokeWidth = strokeWidthAttr
        }
    }


    fun setWidthStroke(width: Float) {
        strokePaint.strokeWidth = width
        invalidate()
    }

    fun setColorStroke(color: Int) {
        strokePaint.color = color
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d("TAG", "onAttachedToWindow: ok")
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, 0f, width.toFloat(), height.toFloat(), strokePaint)
        Log.d("TAG", "onDraw: ok")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //считываем полный размер с отступами
        val heightSize = MeasureSpec.getSize(heightMeasureSpec) + paddingBottom + paddingTop
        val widthSize = MeasureSpec.getSize(widthMeasureSpec) + paddingStart + paddingEnd

        //получаем конечный размер view с учетом режима
        val resolvedHeight = resolveSize(heightSize, heightMeasureSpec)
        val resolvedWidth = resolveSize(widthSize, widthMeasureSpec)

        //устанавливаем итоговые размеры
        setMeasuredDimension(resolvedWidth, resolvedHeight)

        Log.d("TAG", "onMeasure: ok")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d("TAG", "onLayout: ok")
    }

    override fun invalidate() {
        super.invalidate()
        Log.d("TAG", "invalidate: ok")
    }

    override fun requestLayout() {
        super.requestLayout()
        Log.d("TAG", "requestLayout: ok")
    }

}