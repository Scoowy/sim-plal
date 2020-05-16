package org.g2jl.models;

import org.g2jl.utils.UtilGraphics;

import java.awt.*;

/**
 * Clase que pinta un cuadro con el contador de ráfagas de CPU actuales.
 *
 * @author Juan Gahona
 * @version 20.5.15
 */
public class BurstCounter {
    private Dimension topLeft;
    private final Dimension bottomRight = new Dimension(70, 30);
    private int burst;

    /**
     * Constructor de clase.
     *
     * @param burst ráfaga de CPU actual
     */
    public BurstCounter(int burst) {
        this.topLeft = new Dimension(0, 0);
        this.burst = burst;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public void calculatePosition(Dimension extremePoint) {
        topLeft.width = extremePoint.width - bottomRight.width;
        topLeft.height = extremePoint.height - bottomRight.height;
    }

    public void paintCounter(Graphics g) {
        g.setColor(UtilGraphics.BACKGROUND_COLOR_COUNTER);
        g.fillRect(topLeft.width, topLeft.height, bottomRight.width, bottomRight.height);
        g.setColor(UtilGraphics.STROKE_COLOR);
        g.drawRect(topLeft.width, topLeft.height, bottomRight.width, bottomRight.height);

        g.setFont(UtilGraphics.FONT_COUNTER);
        g.setColor(UtilGraphics.TEXT_COLOR);
        Dimension posText = UtilGraphics.centerText(g, topLeft, bottomRight, burst);
        g.drawString(Integer.toString(burst), posText.width, posText.height);
    }
}
