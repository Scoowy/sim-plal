package org.g2jl.utils;

import java.awt.*;

/**
 * Clase que contiene métodos y variables estáticas para trabajar con elementos gráficos.
 *
 * @author Juan Gahona
 * @version 20.5.29
 */
public class UtilGraphics {
    public final static Color STROKE_COLOR = Color.BLACK;
    public final static Color BACKGROUND_COLOR_COUNTER = Color.WHITE;
    public final static Color BACKGROUND_COLOR_CELL = Color.CYAN;
    public final static Color TEXT_COLOR = Color.DARK_GRAY;

    public final static Font FONT_COUNTER = new Font("Segoe UI", Font.BOLD, 26);
    public static Font fontCells;

    public final static int TEXT_LEFT = 0;
    public final static int TEXT_RIGHT = 1;
    public final static int TEXT_CENTER = 2;

    private final static double heightFontCell = 0.4;

    public final static int PADDING_LEFT = 20;
    public final static int PADDING_TOP = 25;
    public final static int PADDING_RIGHT = 20;
    public final static int PADDING_BOTTOM = 22;

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
     */
    public static void calcFontSize(int cellH) {
        int size = (int) Math.floor(cellH * heightFontCell);
        setFontCells(size);
    }

    /**
     * Método que centra un texto vertical y horizontalmente, dentro de un area definida por dos puntos.
     *
     * @param g           Contexto gráfico
     * @param topLeft     Punto inicial del area
     * @param bottomRight Punto final del area
     * @param text        Numero a centrar
     * @param align       alineación respecto de la celda
     * @return Point punto donde dibujarlo
     */
    public static Point alignText(Graphics g, Point topLeft, Point bottomRight, int text, int align) {
        return alignText(g, topLeft, bottomRight, Integer.toString(text), align);
    }

    /**
     * Método que alinea un texto vertical y horizontalmente, dentro de un area definida por dos puntos, en relación a una celda de Gant.
     *
     * @param g           Contexto gráfico
     * @param topLeft     Punto inicial del area
     * @param bottomRight Punto final del area
     * @param text        Texto a centrar
     * @param align       alineación respecto de la celda
     * @return Point punto donde dibujarlo
     */
    public static Point alignText(Graphics g, Point topLeft, Point bottomRight, String text, int align) {
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int widthText = fm.stringWidth(text);
        int heightText = fm.getHeight();

        int pX;
        int pY;

        switch (align) {
            case TEXT_LEFT:
                pX = topLeft.x + bottomRight.x - widthText;
                pY = topLeft.y + bottomRight.y + heightText;
                return new Point(pX, pY);

            case TEXT_RIGHT:
                pX = topLeft.x - widthText;
                pY = topLeft.y + bottomRight.y + heightText;
                return new Point(pX, pY);

            case TEXT_CENTER:
                pX = topLeft.x + ((bottomRight.x - widthText) / 2);
                pY = topLeft.y + ((bottomRight.y - heightText) / 2) + fm.getAscent();
                return new Point(pX, pY);

            default:
                return new Point(0, 0);
        }
    }

    /**
     * Función que calcula el tamaño óptimo que debe tener las celdas en el diagrama de Gant, teniendo en
     * cuenta el numero de procesos que se dibujaran.
     *
     * @param numProcesses el numero de procesos que existen actualmente
     * @param panel        la dimension del contenedor
     * @return Point que determina el tamaño de la celda
     */
    public static Point calcCellSize(int numProcesses, Dimension panel) {
        Dimension cellSize = new Dimension(MAX_SIZE_CELL);
        cellSize.width = panel.width / numProcesses;
        System.out.printf("%d - %d\n", cellSize.width, MIN_SIZE_CELL.width);
        // cellSize.width tendrá el valor mínimo de la celda si es inferior a este.
        cellSize.width = Math.max(cellSize.width, MIN_SIZE_CELL.width);
        System.out.printf("%d\n", cellSize.width);

        if (numProcesses > MIN_NUM_CELLS_ROW) {
            cellSize.width = panel.width / 10;
            // En el caso de ser un numero divisible para 10 el numero de filas es correcto
            // En el caso no ser divisible se suma una fila , ejemplo (int) 19/10 = 1, nos falta 1 fila
            numRowsCells = numProcesses % 10 == 0 ? numProcesses / 10 : (numProcesses / 10) + 1;
            cellSize.height = (panel.height - (SPACE_BETWEEN_CELLS_ROW * numRowsCells)) / numRowsCells;
        }

        return new Point(cellSize.width, cellSize.height);
    }
}
