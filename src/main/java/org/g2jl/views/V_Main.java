/*
 * Created by JFormDesigner on Sun May 10 01:52:31 COT 2020
 */

package org.g2jl.views;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.*;
import org.jdesktop.swingx.*;

/**
 * @author Juan Gahona - Scoowy
 */
public class V_Main extends JXFrame {
    public V_Main() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        xLabel1 = new JXLabel();

        //======== this ========
        setTitle("Ventana principal");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[grow, center]",
                // rows
                "[grow, center]"));

        //---- xLabel1 ----
        xLabel1.setText("Hello World!");
        xLabel1.setFont(new Font("Roboto", Font.PLAIN, 60));
        contentPane.add(xLabel1, "cell 0 0");
        setSize(800, 600);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JXLabel xLabel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
