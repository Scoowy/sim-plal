package org.g2jl.models;

import org.g2jl.utils.UtilData;
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
 * @version 20.5.29
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

//        setBorder(new TitledBorder(null, "Diagrama de Gant", TitledBorder.LEFT, TitledBorder.TOP));

        this.dimensionPanel = getSize();
        this.setPreferredSize(new Dimension(750, 200));
        this.sizeCanvas = getPreferredSize();
        System.out.printf("Constructor: %d - %d\n", sizeCanvas.width, sizeCanvas.height);

        this.counter = new BurstCounter(UtilData.BURST);
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
            this.cells = new ArrayList<>();

            // Calcula el tamaño de las celdas
            Point bottomRight = UtilGraphics.calcCellSize(processes.size(), dimensionPanel);

            Point topLeft = new Point(UtilGraphics.PADDING_LEFT, UtilGraphics.PADDING_TOP);

            AtomicInteger counter = new AtomicInteger();

            UtilGraphics.calcFontSize(bottomRight.y);

            for (Process process : processes) {
                this.cells.add(new CellGant(new Point(topLeft), new Point(bottomRight), process.getName(), process.getWaitTime(), this.counter.getBurst()));

                topLeft.x += bottomRight.x;

                if (counter.addAndGet(1) == 10) {
                    topLeft.x = UtilGraphics.PADDING_LEFT;
                    topLeft.y += UtilGraphics.SPACE_BETWEEN_CELLS_ROW + bottomRight.y;

                    counter.set(0);
                }
            }
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
        List<Process> processes = UtilData.generateProcesses(30);

        super.paintComponent(g);
        recalculateComponents(g);

        processListToCellList(processes);
        paintCells(g);
        counter.paintCounter(g);
    }

}
