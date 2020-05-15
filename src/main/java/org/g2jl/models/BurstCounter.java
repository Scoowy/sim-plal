package org.g2jl.models;

import java.awt.*;

/**
 * Clase que pinta un cuadro con el contador de ráfagas de CPU actuales.
 *
 * @author Juan Gahona
 * @version 20.5.15
 */
public class BurstCounter {
    private int x;
    private int y;
    private final int w = 70;
    private final int h = 30;

    private int burst;

    private final Color STROKE_COLOR = Color.BLACK;
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = Color.DARK_GRAY;

    private Font fuente;
    private Dimension pointFont;

    /**
     * Constructor de clase.
     *
     * @param burst rafaga de CPU actual
     */
    public BurstCounter(int burst) {
        this.burst = burst;
        this.x = 0;
        this.y = 0;

        this.fuente = null;
        this.pointFont = new Dimension(x, y);
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

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public void calculatePosition(Dimension extremePoint) {
        this.x = extremePoint.width - this.w;
        this.y = extremePoint.height - this.h;
    }

    public String changeFontMetrics(Graphics g) {
        Font oldFont = g.getFont();
        this.fuente = new Font(oldFont.getName(), oldFont.getStyle(), 26);
        FontMetrics fm = g.getFontMetrics(fuente);

        String burstStr = Integer.toString(this.burst);
        int anchoBurstStr = fm.stringWidth(burstStr);
        int altoBurstStr = fm.getHeight();
        pointFont.width = x + ((w - anchoBurstStr) / 2);
        pointFont.height = y + (((h - altoBurstStr) / 2) + fm.getAscent());

        return burstStr;
    }

    public void paintCounter(Graphics g) {
        String texto = changeFontMetrics(g);

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(x, y, w, h);
        g.setColor(STROKE_COLOR);
        g.drawRect(x, y, w, h);

        g.setFont(fuente);
        g.setColor(this.TEXT_COLOR);
        g.drawString(texto, pointFont.width, pointFont.height);
    }
}
