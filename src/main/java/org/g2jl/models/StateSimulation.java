package org.g2jl.models;

/**
 * Estados por los cuales pasa la simulación.
 * STOP si la simulación se encuentra sin iniciar.
 * PAUSE si la simulación esta pausada.
 * PLAY si la simulación esta ejecutándose.
 *
 * @author Juan Gahona
 * @version 20.5.29
 */
public enum StateSimulation {
    STOP, PAUSE, PLAY, END_PROCESS
}
