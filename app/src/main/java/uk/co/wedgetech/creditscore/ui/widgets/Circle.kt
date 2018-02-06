package uk.co.wedgetech.creditscore.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class Circle(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint: Paint
    private var rect: RectF
    private val color: Int
    private val strokeWidth: Float

    var angle: Float

    init {
        val set = intArrayOf(android.R.attr.width, android.R.attr.angle, android.R.attr.color  )
        val ta = context.obtainStyledAttributes(attrs, set)
        try {
            color = ta.getColor(2, Color.BLACK)
            strokeWidth = ta.getDimension(0, 10f)
            angle = ta.getFloat(1, 0f)
        } finally {
            ta.recycle()
        }

        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        paint.strokeCap = Paint.Cap.ROUND
        //Circle color
        paint.color = color

        rect = RectF()
    }

    override fun onSizeChanged(xNew: Int, yNew: Int, xOld: Int, yOld: Int) {
        super.onSizeChanged(xNew, yNew, xOld, yOld)
        val strokeWidth = paint.strokeWidth
        rect = RectF(strokeWidth, strokeWidth, xNew.toFloat()-strokeWidth , yNew.toFloat()-strokeWidth )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint)
    }

    companion object {
        private val START_ANGLE_POINT = 270f
    }
}