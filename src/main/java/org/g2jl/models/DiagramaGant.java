package org.g2jl.models;

import org.g2jl.utils.UtilGraphics;
import org.jdesktop.swingx.JXPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase heredada de un JXFrame en la que se dibujara el diagrama de Gant.
 *
 * @author Juan Gahona
 * @version 20.5.30
 * @see "https://stackoverflow.com/a/37460185/230513"
 */
public class DiagramaGant extends JXPanel {
    private List<CellGant> cells;
    private Dimension dimensionPanel;
    private BurstCounter counter;

    private Dimension sizeCanvas;

    /**
     * Constructor de clase
     */
    public DiagramaGant() {
        UtilGraphics.setFontCells();

        this.cells = new ArrayList<>();

        this.dimensionPanel = getSize();
        this.setPreferredSize(new Dimension(750, 200));
        this.sizeCanvas = getPreferredSize();
        System.out.printf("Constructor: %d - %d\n", sizeCanvas.width, sizeCanvas.height);

        this.counter = new BurstCounter();
    }

    public void setBurst(AtomicInteger burst) {
        this.counter.setBurst(burst);
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
     * @param processesFinal lista de procesos
     */
    public void processListToCellList(List<Process> processesFinal, Process processesRun) {
        if (processesFinal.size() != 0) {
            this.cells = new ArrayList<>();
            Point bottomRight = null;
            // Calcula el tamaño de las celdas
            if (processesRun != null) {
                bottomRight = UtilGraphics.calcCellSize(processesFinal.size() + 1, dimensionPanel);
            } else {
                bottomRight = UtilGraphics.calcCellSize(processesFinal.size(), dimensionPanel);
            }

            Point topLeft = new Point(UtilGraphics.PADDING_LEFT, UtilGraphics.PADDING_TOP);

            AtomicInteger counter = new AtomicInteger();

            UtilGraphics.calcFontSize(bottomRight.y);

            for (Process process : processesFinal) {
                this.cells.add(new CellGant(new Point(topLeft), new Point(bottomRight), process.getName(), process.getInitTime(), process.getReturnTime()));

                topLeft.x += bottomRight.x;

                if (counter.addAndGet(1) == 10) {
                    topLeft.x = UtilGraphics.PADDING_LEFT;
                    topLeft.y += UtilGraphics.SPACE_BETWEEN_CELLS_ROW + bottomRight.y;

                    counter.set(0);
                }
            }

            if (processesRun != null) {
                this.cells.add(new CellGant(new Point(topLeft), new Point(bottomRight), processesRun.getName(), processesRun.getInitTime(), this.counter.getBurst().get()));
            }
        } else if (processesRun != null) {
            this.cells = new ArrayList<>();
            Point bottomRight = UtilGraphics.calcCellSize(1, dimensionPanel);
            Point topLeft = new Point(UtilGraphics.PADDING_LEFT, UtilGraphics.PADDING_TOP);
            UtilGraphics.calcFontSize(bottomRight.y);

            this.cells.add(new CellGant(new Point(topLeft), new Point(bottomRight), processesRun.getName(), processesRun.getInitTime(), this.counter.getBurst().get()));
        }
    }

    /**
     * Método que recalcula el tamaño del canvas descontando los padding
     */
    public void recalculateDimension() {
        dimensionPanel = getSize();
        dimensionPanel.width = dimensionPanel.width - UtilGraphics.PADDING_RIGHT * 2;
        dimensionPanel.height = dimensionPanel.height - UtilGraphics.PADDING_BOTTOM * 2;
    }

    public void recalculateComponents(Graphics g) {
        recalculateDimension();
        Dimension panelBRPoint = new Dimension(dimensionPanel.width + UtilGraphics.PADDING_LEFT, dimensionPanel.height + UtilGraphics.PADDING_TOP * 2 - 9);
        counter.calculatePosition(panelBRPoint);
    }

    public void paintTestGraphics(Graphics g) {
        String texto = String.format("%d - %d", dimensionPanel.width, dimensionPanel.height);
        g.drawRect(UtilGraphics.PADDING_LEFT, UtilGraphics.PADDING_TOP, dimensionPanel.width, dimensionPanel.height);
        g.drawString(texto, UtilGraphics.PADDING_LEFT, UtilGraphics.PADDING_TOP);
    }

    /**
     * Método que dibuja sobre el JXPanel
     *
     * @param g contexto de los gráficos
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        recalculateComponents(g);
        paintCells(g);
        counter.paintCounter(g);
    }

}
