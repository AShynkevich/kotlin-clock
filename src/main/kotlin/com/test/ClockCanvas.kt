package com.test

import java.awt.Canvas
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.time.LocalDateTime
import kotlin.math.cos
import kotlin.math.sin

class ClockCanvas(private val passedSize: Dimension) : Canvas() {
    init {
        preferredSize = passedSize
        size = passedSize
    }

    override fun paint(g: Graphics) {
        g.color = Color.BLACK
        g.fillRect(0, 0, passedSize.width, passedSize.height)

        drawClockDeck(g)
        drawClockHands(g)
        repaint()
    }

    private fun drawClockDeck(g: Graphics) {
        g.color = Color.WHITE
        clockDrawer(360 / 60) { x, y ->
            val width = 4
            g.fillOval(x - width / 2, y - width / 2, width, width)
        }

        g.color = Color.YELLOW
        clockDrawer(360 / 12) { x, y ->
            val width = 10
            g.drawRect(x - width / 2, y - width / 2, width, width)
        }
    }

    private fun clockDrawer(step: Int, block: (x: Int, y: Int) -> Unit) {
        for (i in 0..360 step step) {
            val x = getXCenter() + (getXCenter() * 0.8 * cos(Math.toRadians(i.toDouble()))).toInt()
            val y = getYCenter() + (getYCenter() * 0.8 * sin(Math.toRadians(i.toDouble()))).toInt()
            block(x, y)
        }
    }

    private fun drawClockHands(g: Graphics) {
        val time = LocalDateTime.now()

        val secondAngle = 360 / 60 * time.second
        val minuteAngle = 360 / 60 * time.minute
        val hourAngle = 360 / 12 * time.hour

        g.color = Color.RED
        drawHand(g, secondAngle, 0.7)
        g.color = Color.GREEN
        drawHand(g, minuteAngle, 0.5)
        g.color = Color.BLUE
        drawHand(g, hourAngle, 0.3)
    }

    private fun drawHand(g: Graphics, secondAngle: Int, handSize: Double) {
        val x = getXCenter() + (getXCenter() * handSize * cos(Math.toRadians(secondAngle.toDouble()) - Math.PI / 2)).toInt()
        val y = getYCenter() + (getYCenter() * handSize * sin(Math.toRadians(secondAngle.toDouble()) - Math.PI / 2)).toInt()
        g.drawLine(getXCenter(), getYCenter(), x, y)
    }

    private fun getXCenter(): Int {
        return passedSize.width / 2
    }

    private fun getYCenter(): Int {
        return passedSize.height / 2
    }
}