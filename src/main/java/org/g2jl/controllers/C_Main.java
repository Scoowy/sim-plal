package org.g2jl.controllers;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialPalenightContrastIJTheme;
import org.g2jl.interfaces.I_Controller;
import org.g2jl.views.V_Main;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class C_Main implements I_Controller {
    public V_Main view;
//    public M_Main model;

    public C_Main(V_Main view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnMensaje()) {
            System.out.println("Boton pulsado");
            try {
                UIManager.setLookAndFeel(new FlatMaterialPalenightContrastIJTheme());
            } catch (IllegalMonitorStateException ex) {
                System.err.println(ex.getCause().getMessage());
            } catch (Exception ex) {
                System.err.println("Error al cargar el tema oscuro");
            }
        }
    }
}
