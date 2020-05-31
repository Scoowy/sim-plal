package org.g2jl.models;

import org.g2jl.utils.UtilGraphics;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que pinta un cuadro con el contador de ráfagas de CPU actuales.
 *
 * @author Juan Gahona
 * @version 20.5.30
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

    public void setBurst(AtomicInteger burst) {
        this.burst = burst;
    }

    public void calculatePosition(Dimension extremePoint) {
        topLeft.x = extremePoint.width - bottomRight.x;
        topLeft.y = extremePoint.height - bottomRight.y;
    }

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
