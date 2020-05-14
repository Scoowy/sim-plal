package org.g2jl.models;

import org.jdesktop.swingx.JXPanel;

import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase heredada de un JXFrame en la que se dibujara el diagrama de Gant.
 *
 * @author Juan Gahona
 * @version 20.5.13
 */
public class DiagramaGant extends JXPanel {
    private List<CellGant> cells;
    private Dimension dimensionPanel;
    private BurstCounter counter;

    // Padding Left Top Right Bottom
    private final int[] padds = {20, 20, 20, 20};

    public DiagramaGant() {
        setBorder(new TitledBorder(null, "Diagrama de Gant", TitledBorder.LEFT, TitledBorder.TOP));
//        setBackground(Color.BLUE);
        cells = new ArrayList<CellGant>();
        dimensionPanel = getSize();
        counter = new BurstCounter(123);
    }

    public void processListToCellList(List<Process> process) {

    }

    public void recalculateDimension() {
        dimensionPanel = getSize();
        dimensionPanel.width = dimensionPanel.width - padds[2] * 2;
        dimensionPanel.height = dimensionPanel.height - padds[3] * 2;
    }

    public void recalculateComponents(Graphics g) {
        recalculateDimension();
        Dimension panelBRPoint = new Dimension(dimensionPanel.width + padds[0], dimensionPanel.height + padds[1] * 2 - 3);
        counter.calculatePosition(panelBRPoint);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        recalculateComponents(g);
        String texto = String.format("%d - %d", dimensionPanel.width, dimensionPanel.height);
        g.drawRect(padds[0], padds[1], dimensionPanel.width, dimensionPanel.height);

        g.drawChars(texto.toCharArray(), 0, texto.length(), 10, 20);
        counter.paintCounter(g);
    }
}
