package org.g2jl.models;

/**
 * Estados por los cuales pasan los procesos.
 * PREPARADO para los procesos que están en la lista de carga.
 * PROCESANDO para el proceso que se encuentra en ejecución.
 * ESPERANDO para los procesos que están en cola y no se están procesando.
 * TERMINADO para los procesos que ya se ejecutaron.
 *
 * @author Juan Gahona
 * @version 20.5.29
 */
public enum StateProcess {
    PREPARADO, PROCESANDO, ESPERANDO, TERMINADO
}
