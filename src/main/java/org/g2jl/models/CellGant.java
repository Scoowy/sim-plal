package org.g2jl.models;

import org.g2jl.utils.UtilGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Clase representativa de una celda en el diagrama e Gant
 *
 * @author Juan Gahona
 * @version 20.5.30
 */
public class CellGant {

    private final Point TOP_LEFT;
    private final Point BOTTOM_RIGHT;

    private final String VALUE;
    private final String INIT_TIME;
    private final String FINAL_TIME;

    private final boolean FIRST;

    private final Color STROKE_COLOR = Color.BLACK;

    /**
     * Constructor de clase
     *
     * @param topLeft     punto Top-Left
     * @param bottomRight punto Bottom-Right
     * @param value       ráfaga del proceso
     * @param initTime    tiempo inicial del proceso
     */
    public CellGant(Point topLeft, Point bottomRight, String value, int initTime, int finalTime, boolean first) {
        this.TOP_LEFT = topLeft;
        this.BOTTOM_RIGHT = bottomRight;

        this.VALUE = value;
        this.INIT_TIME = Integer.toString(initTime);
        this.FINAL_TIME = Integer.toString(finalTime);

        this.FIRST = first;
    }

    /**
     * Método que dibuja una celda con sus datos.
     *
     * @param g Los gráficos del JXFrame
     */
    public void paintCell(Graphics g) {
        g.setColor(UtilGraphics.BACKGROUND_COLOR_CELL);
        g.fillRect(TOP_LEFT.x, TOP_LEFT.y, BOTTOM_RIGHT.x, BOTTOM_RIGHT.y);
        g.setColor(STROKE_COLOR);
        g.drawRect(TOP_LEFT.x, TOP_LEFT.y, BOTTOM_RIGHT.x, BOTTOM_RIGHT.y);

        g.setFont(UtilGraphics.fontCells);

        Point textPoint = UtilGraphics.alignText(g, TOP_LEFT, BOTTOM_RIGHT, VALUE, UtilGraphics.TEXT_CENTER);
        g.setColor(UtilGraphics.TEXT_COLOR);
        g.drawString(VALUE, textPoint.x, textPoint.y);

        if (FIRST) {
            Point initTimePoint = UtilGraphics.alignText(g, TOP_LEFT, BOTTOM_RIGHT, INIT_TIME, UtilGraphics.TEXT_RIGHT);
            g.setColor(UtilGraphics.TEXT_COLOR);
            g.drawString(INIT_TIME, initTimePoint.x, initTimePoint.y);
        }

        Point finalTimePoint = UtilGraphics.alignText(g, TOP_LEFT, BOTTOM_RIGHT, FINAL_TIME, UtilGraphics.TEXT_LEFT);
        g.setColor(UtilGraphics.TEXT_COLOR);
        g.drawString(FINAL_TIME, finalTimePoint.x, finalTimePoint.y);
    }
}
