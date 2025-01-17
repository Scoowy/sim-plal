package org.g2jl.models;

import org.g2jl.utils.UtilGraphics;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que pinta un cuadro con el contador de ráfagas de CPU actuales.
 *
 * @author Juan Gahona
 * @version 20.5.31
 */

public class BurstCounter {
    private final Point topLeft;
    private final Point bottomRight = new Point(70, 30);
    private AtomicInteger burst;

    /**
     * Constructor de clase.
     */
    public BurstCounter() {
        this.topLeft = new Point(0, 0);
        this.burst = null;
    }

    public AtomicInteger getBurst() {
        return burst;
    }

    /**
     * Damos valores para ráfagas
     *
     * @param burst AtomicInteger
     */
    public void setBurst(AtomicInteger burst) {
        this.burst = burst;
    }

    /**
     * Calculamos la posición de dibujado del contador otorgando un punto extremo para luego
     * evaluarlo con cada uno de los ejes X y Y tanto con el alto como del ancho
     *
     * @param extremePoint Dimension del extremo derecho inferior del counter
     */
    public void calculatePosition(Dimension extremePoint) {
        topLeft.x = extremePoint.width - bottomRight.x;
        topLeft.y = extremePoint.height - bottomRight.y;
    }

    /**
     * Método para pintar cuadro de contador de ráfagas del CPU
     *
     * @param g Contexto gráfico
     */
    public void paintCounter(Graphics g) {
        g.setColor(UtilGraphics.BACKGROUND_COLOR_COUNTER);
        g.fillRect(topLeft.x, topLeft.y, bottomRight.x, bottomRight.y);
        g.setColor(UtilGraphics.STROKE_COLOR);
        g.drawRect(topLeft.x, topLeft.y, bottomRight.x, bottomRight.y);
        g.setFont(UtilGraphics.FONT_COUNTER);
        g.setColor(UtilGraphics.TEXT_COLOR);

        int burstFix = Math.max(burst.get() - 1, 0);

        Point posText = UtilGraphics.alignText(g, topLeft, bottomRight, burstFix, UtilGraphics.TEXT_CENTER);
        g.drawString(Integer.toString(burstFix), posText.x, posText.y);
    }
}
