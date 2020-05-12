package org.g2jl.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase Interface <b>I_Controller</b>
 *
 * @author Juan Gahona
 * @version 20.5.10
 */
public interface I_Controller extends ActionListener {
    @Override
    public void actionPerformed(ActionEvent e);
}