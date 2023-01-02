package com.go0ose.agedeterminant.presentation.customView

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.go0ose.agedeterminant.R

class LoadingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeWidth = 4f
    }

    private val rect = RectF()

    private var sweepAngle = 0f

    private val animator = ValueAnimator.ofFloat(0f, 360f).apply {
        duration = 1000
        repeatCount = ValueAnimator.INFINITE
        addUpdateListener { valueAnimator ->
            sweepAngle = valueAnimator.animatedValue as Float
            invalidate()
        }
    }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.LoadingView, 0, 0).apply {
            try {
                paint.color = getColor(R.styleable.LoadingView_color, Color.BLUE)
            } finally {
                recycle()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(0f, 0f, width + 0f, height.toFloat(), 90f, sweepAngle, true, paint)
    }
}
