package org.g2jl.models;

import javafx.scene.control.Cell;
import org.g2jl.controllers.C_Canvas;
import org.g2jl.utils.UtilGraphics;
import org.jdesktop.swingx.JXPanel;

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

        setBorder(new TitledBorder(null, "Diagrama de Gant", TitledBorder.LEFT, TitledBorder.TOP));
//        setBackground(Color.BLUE);
        cells = new ArrayList<CellGant>();
        dimensionPanel = getSize();
        counter = new BurstCounter(123);

        addListeners();

    }

    public void addListeners() {
        addMouseListener(controller);
    }

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

            Dimension cellSize = calculateCellSize(processes.size());

            int xPoint = padds[0];
            int yPoint = padds[1];

            for (Process process : processes) {
                this.cells.add(new CellGant(xPoint, yPoint, cellSize.width, cellSize.height, process.getBurstNum()));
            }
        }

    }

    /**
     * Método que calcula el ancho y alto de las celdas
     *
     * @param numProcess numero de procesos
     * @return punto Right-Bottom de la celda
     */
    public Dimension calculateCellSize(int numProcess) {
        int maxH = 80;
        int numRows;

        int cellH = maxH;
        int cellW = (int) Math.floor(dimensionPanel.width / numProcess);

        // Solo si el ancho de la celda es menor al ancho minimo se establece este como valor
        if (cellW < minSizeCell.width) {
            cellW = minSizeCell.width;
        }

        // Si existen mas de 10 procesos se empiezan a crear filas debajo de estos.
        if (numProcess > minNumCells) {
            numRows = (int) Math.floor(numProcess / 10);
            cellH = (int) Math.floor((dimensionPanel.height - spaceBetweenCellsRow * (numRows - 1)) / numRows);
        }


        return new Dimension(cellW, cellH);
    }

    /**
     * Método que recalcula el tamaño del canvas descontando los padding
     */
    public void recalculateDimension() {
        dimensionPanel = getSize();
        dimensionPanel.width = dimensionPanel.width - padds[2] * 2;
        dimensionPanel.height = dimensionPanel.height - padds[3] * 2;
    }

    public void recalculateComponents(Graphics g) {
        recalculateDimension();
        Dimension panelBRPoint = new Dimension(dimensionPanel.width + padds[0], dimensionPanel.height + padds[1] * 2 - 9);
        counter.calculatePosition(panelBRPoint);
    }

    /**
     * Método que dibuja sobre el JXPanel
     *
     * @param g contexto de los gráficos
     */
    public void paintComponent(Graphics g) {
        List<Process> processes = new ArrayList<Process>();
        processes.add(new Process(1, "P1", 0, 5, 1));
        processes.add(new Process(2, "P2", 0, 5, 1));
        processes.add(new Process(3, "P3", 0, 5, 1));
        processes.add(new Process(4, "P4", 0, 5, 1));
        processes.add(new Process(5, "P5", 0, 5, 1));

        super.paintComponent(g);
        recalculateComponents(g);
        String texto = String.format("%d - %d", dimensionPanel.width, dimensionPanel.height);
        g.drawRect(padds[0], padds[1], dimensionPanel.width, dimensionPanel.height);

        g.drawChars(texto.toCharArray(), 0, texto.length(), 10, 20);

        processListToCellList(processes);
        paintCells(g);
        counter.paintCounter(g);
    }
}
