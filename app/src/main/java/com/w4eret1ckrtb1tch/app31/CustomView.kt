package com.w4eret1ckrtb1tch.app31

import android.content.Context
import android.graphics.*
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
            style = Paint.Style.FILL_AND_STROKE
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

    // TODO: 06.08.2021 31.6. Paint
    private val colorGradient = Paint().apply {
        style = Paint.Style.FILL
        flags = Paint.ANTI_ALIAS_FLAG
    }


    private val colors = intArrayOf(
        Color.YELLOW,
        Color.BLUE,
        Color.CYAN,
        Color.MAGENTA,
        Color.RED
    )
    private val positions = floatArrayOf(0.2f, 0.3f, 0.5f, 0.6f, 0.7f)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        colorGradient.shader =
//            LinearGradient(0f, 0f, 0f, h.toFloat(), Color.RED, Color.YELLOW, Shader.TileMode.MIRROR)

//        colorGradient.shader =
//            LinearGradient(0f, 0f, 0f, h.toFloat(), colors, positions, Shader.TileMode.MIRROR)
//        colorGradient.shader =

//            RadialGradient(
//                w.toFloat() / 2,
//                h.toFloat() / 2,
//                100f,
//                Color.RED,
//                Color.YELLOW,
//                Shader.TileMode.MIRROR
//            )

        colorGradient.shader =
            RadialGradient(
                w.toFloat() / 2,
                h.toFloat() / 2,
                100f, colors, positions,
                Shader.TileMode.MIRROR
            )
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d("TAG", "onAttachedToWindow: ok")
    }


    val color = Color.argb(100, 255, 87, 34)


    //произвольная фигура
    private val a = Point(0, 0)
    private val b = Point(0, 100)
    private val c = Point(87, 50)

    val path = Path().apply {
        //тип заполнения краской
        fillType = Path.FillType.EVEN_ODD
        //Рисуем путь по точкам, созданным ранее
        lineTo(b.x.toFloat(), b.y.toFloat())
        lineTo(c.x.toFloat(), c.y.toFloat())
        lineTo(a.x.toFloat(), a.y.toFloat())
        close()
    }

    private val textPath = Path()

    private val textColor = Paint().apply {
        color = Color.BLUE
        textSize = 100f
        typeface = Typeface.SANS_SERIF
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true

    }

    override fun onDraw(canvas: Canvas?) {

        textPath.addCircle(width.toFloat() / 2, height.toFloat() / 2, 400f, Path.Direction.CW)

        canvas?.let {
            //текст
            it.drawCircle(width.toFloat() / 2, height.toFloat() / 2, 400f, colorGradient)
            it.drawTextOnPath("CustomView", textPath, 0f, 0f, textColor)
//            //заливка
//            it.drawColor(Color.parseColor("#FF5722"))
//            //линии
//            it.drawLine(0f, 0f, width.toFloat(), height.toFloat(), strokePaint)
//            it.drawLine(width.toFloat(), 0f, 0f, height.toFloat(), strokePaint)
//            //окружность
//            it.drawCircle(width.toFloat() / 2,height.toFloat()/2,200f,getPaintColor(Color.RED))
//            //прямоугольник
//            it.drawRect(
//                width.toFloat() / 2 - 100f,
//                height.toFloat() / 2 - 100f,
//                width.toFloat() / 2 + 100f,
//                height.toFloat() / 2 + 100f,
//                getPaintColor(Color.GREEN)
//            )
//            //путь
//            it.drawPath(path,getPaintColor(Color.YELLOW))


        }
        Log.d("TAG", "onDraw: ok")
    }


    private fun getPaintColor(strokeColor: Int): Paint {
        return Paint().apply {
            color = strokeColor
            style = Paint.Style.FILL_AND_STROKE
            flags = Paint.ANTI_ALIAS_FLAG
        }
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