package uk.co.wedgetech.creditscore.ui.widgets

import android.view.animation.Animation
import android.view.animation.Transformation


class CircleAngleAnimation(private val circle: Circle, private val newAngle: Int) : Animation() {

    private val oldAngle: Float

    init {
        this.oldAngle = circle.angle
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        circle.angle = oldAngle + (newAngle - oldAngle) * interpolatedTime
        circle.requestLayout()
    }
}