package org.g2jl.simplal;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialPalenightContrastIJTheme;
import org.g2jl.views.V_Main;

import javax.swing.*;

public class SimPlAl {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightOwlContrastIJTheme());
        } catch (Exception ex) {
            System.err.println("Fallo al cargar el tema.");
        }

        V_Main vMain = new V_Main();
        vMain.setVisible(true);
    }
}
