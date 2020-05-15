package org.g2jl.models;

import java.awt.*;

/**
 * Clase representativa de una celda en el diagrama e Gant
 *
 * @author Juan Gahona
 * @version 20.5.15
 */
public class CellGant {
    private final int X;
    private final int Y;
    private final int W;
    private final int H;

    private final String VALUE;

    private final Color STROKE_COLOR = Color.BLACK;
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = Color.DARK_GRAY;

    /**
     * Constructor de clase
     *
     * @param x     punto Top-Left
     * @param y     punto Top-Left
     * @param w     punto Bottom-Right
     * @param h     punto Bottom-Right
     * @param value ráfaga del proceso
     */
    public CellGant(int x, int y, int w, int h, int value) {
        this.X = x;
        this.Y = y;
        this.W = w;
        this.H = h;

        this.VALUE = Integer.toString(value);
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }

    public String getValue() {
        return VALUE;
    }

    /**
     * Método que dibuja una celda con sus datos.
     *
     * @param g Los gráficos del JXFrame
     */
    public void paintCell(Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(X, Y, W, H);
        g.setColor(STROKE_COLOR);
        g.drawRect(X, Y, W, H);
    }
}
