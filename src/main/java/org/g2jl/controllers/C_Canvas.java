package org.g2jl.controllers;

import org.g2jl.interfaces.I_Controller;
import org.g2jl.models.DiagramaGant;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Clase controladora del canvas
 *
 * @author Juan Gahona
 * @version 20.5.14
 */
public class C_Canvas implements I_Controller, MouseListener {
    private DiagramaGant view;

    public C_Canvas(DiagramaGant view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Método que me imprime en consola donde se hizo un clic dentro del canvas
     * Motivos de DEBUG
     *
     * @param e evento del ratón
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("BTN: %s at: %d - %d\n", e.getButton(), e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void repaintCanvas() {
        this.view.repaint();
    }

}
