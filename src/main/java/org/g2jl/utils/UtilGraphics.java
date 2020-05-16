package org.g2jl.utils;

import java.awt.*;

/**
 * Clase que contiene métodos y variables estáticas para trabajar con elementos gráficos.
 *
 * @author Juan Gahona
 * @version 20.5.15
 */
public class UtilGraphics {
    public final static Color STROKE_COLOR = Color.BLACK;
    public final static Color BACKGROUND_COLOR_COUNTER = Color.WHITE;
    public final static Color BACKGROUND_COLOR_CELL = Color.CYAN;
    public final static Color TEXT_COLOR = Color.DARK_GRAY;

    public final static Font FONT_COUNTER = new Font("Segoe UI", Font.BOLD, 26);
    public static Font fontCells;

    private final static double heightFontCell = 0.4;

    /**
     * Método que establece una fuente por defecto para las celdas del diagrama de Gant
     */
    public static void setFontCells() {
        fontCells = new Font("Segoe UI", Font.BOLD, 32);
    }

    /**
     * Método que establece una fuente por defecto para las celdas del diagrama de Gant
     *
     * @param size tamaño de la fuente
     */
    private static void setFontCells(int size) {
        fontCells = new Font("Segoe UI", Font.BOLD, size);
    }

    /**
     * Método que calcula el tamaño de la fuente respecto al tamaño de la celda
     *
     * @param cellH height de la celda
     * @return el tamaño de la fuente
     */
    public static int calcFontSize(int cellH) {
        int size = (int) Math.floor(cellH * heightFontCell);
        setFontCells(size);
        return size;
    }

    /**
     * Método que centra un numero vertical y horizontalmente, dentro de un area definida por dos puntos.
     *
     * @param g           Contexto gráfico
     * @param topLeft     Punto inicial del area
     * @param bottomRight Punto final del area
     * @param value       Numero a centrar
     * @return Dimension punto donde dibujarlo
     */
    public static Dimension centerText(Graphics g, Dimension topLeft, Dimension bottomRight, int value) {
        return centerText(g, topLeft, bottomRight, Integer.toString(value));
    }

    /**
     * Método que centra un texto vertical y horizontalmente, dentro de un area definida por dos puntos.
     *
     * @param g           Contexto gráfico
     * @param topLeft     Punto inicial del area
     * @param bottomRight Punto final del area
     * @param text        Texto a centrar
     * @return Dimension punto donde dibujarlo
     */
    public static Dimension centerText(Graphics g, Dimension topLeft, Dimension bottomRight, String text) {
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int widthText = fm.stringWidth(text);
        int heightText = fm.getHeight();

        int pX = topLeft.width + ((bottomRight.width - widthText) / 2);
        int pY = topLeft.height + ((bottomRight.height - heightText) / 2) + fm.getAscent();

        return new Dimension(pX, pY);
    }
}
