package com.test

import java.awt.Dimension
import javax.swing.JFrame

class Display(width: Int, height: Int) {
    private val frame = JFrame("Kotlin clock")
    private val size = Dimension(width, height)
    private var canvas = ClockCanvas(size)

    init {
        frame.size = size
        frame.maximumSize = size
        frame.minimumSize = size
        frame.isResizable = false
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)

        frame.isVisible = true
        frame.add(canvas)
    }

}

