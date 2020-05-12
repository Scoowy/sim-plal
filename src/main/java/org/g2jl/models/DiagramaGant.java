package org.g2jl.models;

import org.jdesktop.swingx.JXPanel;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase heredada de un JXFrame en la que se dibujara el diagrama de Gant.
 *
 * @author Juan Gahona
 * @version 20.5.12
 */
public class DiagramaGant extends JXPanel {
    private List<CellGant> cells;
    private Dimension dimensionPanel;

    public DiagramaGant() {
        setBorder(new TitledBorder(null, "Diagrama de Gant", TitledBorder.LEFT, TitledBorder.TOP));
        setBackground(Color.BLUE);
        cells = new ArrayList<CellGant>();
        dimensionPanel = getSize();
    }

    public void processListToCellList(List<Process> process) {

    }

    public void recalculateDimension() {
        dimensionPanel = getSize();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        recalculateDimension();
        String texto = String.format("%d - %d", dimensionPanel.width, dimensionPanel.height);
        g.drawString(texto, 10, 20);
    }
}
