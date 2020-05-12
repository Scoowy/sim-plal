package org.g2jl.models;

import java.awt.*;

/**
 * Clase representativa de una celda en el diagrama e Gant
 *
 * @author Juan Gahona
 * @version 20.5.12
 */
public class CellGant {
    private int x;
    private int y;
    private int w;
    private int h;

    private String text;

    private Color strokeColor;
    private Color backgroundColor;
    private Color textColor;

    public CellGant(int x, int y, int w, int h, String text) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.text = text;

        this.strokeColor = Color.BLACK;
        this.backgroundColor = Color.WHITE;
        this.textColor = Color.DARK_GRAY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }


    /**
     * Método que dibuja una celda con sus datos.
     *
     * @param g Los gráficos del JXFrame
     */
    public void paintCell(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(x, y, w, h);
        g.setColor(strokeColor);
        g.drawRect(x, y, w, h);
    }
}
