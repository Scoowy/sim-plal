package org.g2jl.models;

import javafx.scene.control.Cell;
import org.g2jl.controllers.C_Canvas;
import org.g2jl.utils.UtilData;
import org.g2jl.utils.UtilGraphics;
import org.jdesktop.swingx.JXPanel;

import javax.rmi.CORBA.Util;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase heredada de un JXFrame en la que se dibujara el diagrama de Gant.
 *
 * @author Juan Gahona
 * @version 20.5.15
 */
public class DiagramaGant extends JXPanel {
    private C_Canvas controller;
    private List<CellGant> cells;
    private Dimension dimensionPanel;
    private BurstCounter counter;

    private final Dimension minSizeCell = new Dimension(60, 30);
    // Producto de el minimo ancho del canvas posible dividido para minSizeCell.width
    private final int minNumCells = 10;
    private final int spaceBetweenCellsRow = 10;

    // Padding Left Top Right Bottom
    private final int[] padds = {20, 25, 20, 22};

    /**
     * Constructor de clase
     */
    public DiagramaGant() {
        UtilGraphics.setFontCells();

        this.controller = new C_Canvas(this);
        this.cells = new ArrayList<CellGant>();

        setBorder(new TitledBorder(null, "Diagrama de Gant", TitledBorder.LEFT, TitledBorder.TOP));

        this.dimensionPanel = getSize();
        this.counter = new BurstCounter(123);

        addListeners();

    }

    public void addListeners() {
        addMouseListener(controller);
    }

    /**
     * Método que pinta cada celda
     *
     * @param g Contexto gráfico
     */
    public void paintCells(Graphics g) {
        for (CellGant cell : this.cells) {
            cell.paintCell(g);
        }
    }

    /**
     * Método que crea una celda por cada proceso
     *
     * @param processes lista de procesos
     */
    public void processListToCellList(List<Process> processes) {
        if (processes.size() != 0) {
            this.cells = new ArrayList<CellGant>();

            // Calcula el tamaño de las celdas
            Dimension bottomRight = UtilGraphics.calcCellSize(processes.size(), dimensionPanel);

            int xPoint = UtilGraphics.P_LEFT;
            int yPoint = UtilGraphics.P_TOP;

            int counter = 0;

            for (Process process : processes) {
                this.cells.add(new CellGant(xPoint, yPoint, bottomRight.width, bottomRight.height, process.getBurstNum()));
                counter += 1;

                xPoint += bottomRight.width;

                if (counter == 10) {
                    xPoint = UtilGraphics.P_LEFT;
                    yPoint += UtilGraphics.SPACE_BETWEEN_CELLS_ROW + bottomRight.height;

                    counter = 0;
                }
            }
        }

    }

    /**
     * Método que recalcula el tamaño del canvas descontando los padding
     */
    public void recalculateDimension() {
        dimensionPanel = getSize();
        dimensionPanel.width = dimensionPanel.width - UtilGraphics.P_RIGHT * 2;
        dimensionPanel.height = dimensionPanel.height - UtilGraphics.P_BOTTOM * 2;
    }

    public void recalculateComponents(Graphics g) {
        recalculateDimension();
        Dimension panelBRPoint = new Dimension(dimensionPanel.width + UtilGraphics.P_LEFT, dimensionPanel.height + UtilGraphics.P_TOP * 2 - 9);
        counter.calculatePosition(panelBRPoint);
    }

    public void paintTestGraphics(Graphics g) {
        String texto = String.format("%d - %d", dimensionPanel.width, dimensionPanel.height);
        g.drawRect(UtilGraphics.P_LEFT, UtilGraphics.P_TOP, dimensionPanel.width, dimensionPanel.height);
        g.drawString(texto, UtilGraphics.P_LEFT, UtilGraphics.P_TOP);
    }

    /**
     * Método que dibuja sobre el JXPanel
     *
     * @param g contexto de los gráficos
     */
    public void paintComponent(Graphics g) {
        List<Process> processes = UtilData.generateProcesses(45);

        super.paintComponent(g);
        recalculateComponents(g);

        paintTestGraphics(g);

        processListToCellList(processes);
        paintCells(g);
        counter.paintCounter(g);
    }
}
