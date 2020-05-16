package org.g2jl.utils;

import java.util.ArrayList;

import org.g2jl.models.Process;

/**
 * Clase generadora de datos de prueba.
 *
 * @author Juan Gahona
 * @version 20.5.16
 */
public class UtilData {
    private final static String PROCESS_NAME = "P";
    public final static int BURST = 999;

    public static ArrayList<Process> generateProcesses(int numProcesses) {
        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 1; i <= numProcesses; i++) {
            String name = String.format("%s%d", PROCESS_NAME, i);
            processes.add(new Process(i, name, 0, 0, 0));
        }

        return processes;
    }
}
