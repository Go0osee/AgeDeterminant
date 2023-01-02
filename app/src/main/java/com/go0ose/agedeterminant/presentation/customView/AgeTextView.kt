package com.go0ose.agedeterminant.presentation.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.go0ose.agedeterminant.R

class AgeTextView(
    context: Context,
    attrs: AttributeSet
) : AppCompatTextView(context, attrs) {

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val numberPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    var number: Int = 0
    var color: Int = 0
        set(value) {
            circlePaint.color = value
            field = value
        }
    var numberColor: Int = 0
        set(value) {
            numberPaint.color = value
            field = value
        }

    init {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.AgeTextView, 0, 0
        ).apply {
            numberColor =
                getColor(R.styleable.AgeTextView_numberColor, context.getColor(R.color.black))
            color = getColor(R.styleable.AgeTextView_circleColor, context.getColor(R.color.green))
            number = getInt(R.styleable.AgeTextView_number, 0)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        when {
            widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY -> {
                if (widthMeasureSpec > heightMeasureSpec) {
                    setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
                } else {
                    setMeasuredDimension(heightMeasureSpec, heightMeasureSpec)
                }
            }

            widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
            }

            widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.EXACTLY -> {
                setMeasuredDimension(heightMeasureSpec, heightMeasureSpec)
            }

            else -> setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(width / 2f, height / 2f, width / 2f, circlePaint)

        numberPaint.textSize = width / 3.5f

        val text = number.toString()
        val textWidth = numberPaint.measureText(text)
        val x = (width - textWidth) / 2f
        val y = height / 2f - (numberPaint.descent() + numberPaint.ascent()) / 2f
        canvas.drawText(text, x, y, numberPaint)
    }
}