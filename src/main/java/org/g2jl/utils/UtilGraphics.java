package org.g2jl.utils;

import javax.swing.plaf.DimensionUIResource;
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

    public final static int P_LEFT = 20;
    public final static int P_TOP = 25;
    public final static int P_RIGHT = 20;
    public final static int P_BOTTOM = 22;

    // START Celdas
    public final static Dimension MIN_SIZE_CELL = new Dimension(60, 30);
    public final static Dimension MAX_SIZE_CELL = new Dimension(9999, 80);
    public final static int SPACE_BETWEEN_CELLS_ROW = 25;
    public final static int MIN_NUM_CELLS_ROW = 10;
    public static int numRowsCells = 0;
    // END Celdas

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

    public static Dimension calcCellSize(int numProcesses, Dimension panel) {
        Dimension cellSize = new Dimension(MAX_SIZE_CELL);
        cellSize.width = panel.width / numProcesses;
        System.out.printf("%d - %d\n", cellSize.width, MIN_SIZE_CELL.width);
        // cellSize.width tendrá el valor minimo de la celda si es inferior a este.
        cellSize.width = Math.max(cellSize.width, MIN_SIZE_CELL.width);
        System.out.printf("%d\n", cellSize.width);

        if (numProcesses > MIN_NUM_CELLS_ROW) {
            cellSize.width = panel.width / 10;
            // En el caso de ser un numero divisible para 10 el numero de filas es correcto
            // En el caso no ser divisible se suma una fila , ejemplo (int) 19/10 = 1, nos falta 1 fila
            numRowsCells = numProcesses % 10 == 0 ? numProcesses / 10 : (numProcesses / 10) + 1;
            cellSize.height = (panel.height - (SPACE_BETWEEN_CELLS_ROW * numRowsCells)) / numRowsCells;
        }

        return cellSize;
    }
}
