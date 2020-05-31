package org.g2jl.simplal;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import org.g2jl.views.V_Main;

import javax.swing.UIManager;

/**
 * <h1>SJF</h1>
 * <hr>
 * Significa (<b>Short Job First</b> – El trabajo más corto primero) se refiere al proceso que tenga el próximo ciclo de
 * CPU más corto. La idea es escoger entre todos los procesos listos el que tenga su próximo ciclo de CPU más pequeño,
 * que asocia a cada proceso la longitud de la siguiente ráfaga de CPU de ese proceso. Cuando la CPU queda disponible,
 * asigna al proceso cuya siguiente ráfaga de CPU sea más corta. Si hay dos procesos cuyas siguientes ráfagas de CPU
 * tienen la misma duración, se emplea planificación FiFo
 *
 * @author Juan Gahona
 * @author Jorge Sarmiento
 * @author Luisa Bermeo
 * @version 20.5.30
 */

/**
 * Ejecución del programa
 */
public class SimPlAl {
    public static void main(String[] args) {
        // Se carga por defecto un tema para toda la interfaz de swing si es posible
        try {
            UIManager.setLookAndFeel(new FlatArcOrangeIJTheme());
        } catch (Exception ex) {
            System.err.println("Fallo al cargar el tema.");
        }

        // Inicializamos la ventana principal que contendrá toda nuestra instancia de la aplicación
        V_Main vMain = new V_Main();
        // La hacemos visible
        vMain.setVisible(true);
    }
}
