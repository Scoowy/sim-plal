package org.g2jl.models;


import org.jdesktop.swingx.JXPanel;

import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase heredada de un JXFrame en la que se dibujara el diagrama de Gant.
 *
 * @author Juan Gahona
 * @version 20.5.12
 */
public class DiagramaGant extends JXPanel {
    List<CellGant> cells;

    public DiagramaGant() {
        setBorder(new TitledBorder(null, "Diagrama de Gant", TitledBorder.LEFT, TitledBorder.TOP));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e
            ) {
                if ("bord\u0065r".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });

        cells = new ArrayList<CellGant>();
    }
}
