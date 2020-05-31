package org.g2jl.utils;

import java.util.ArrayList;

import org.g2jl.models.M_Main;
import org.g2jl.models.Process;

/**
 * Clase generadora de datos de prueba.
 *
 * @author Juan Gahona
 * @version 20.5.30
 */
public class UtilData {
    private final static String PROCESS_NAME = "P";
    public final static int BURST = 0;

    public static ArrayList<Process> generateProcesses(int numProcesses) {
        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 1; i <= numProcesses; i++) {
            String name = String.format("%s%d", PROCESS_NAME, i);
            processes.add(new Process(i, name, randomBetween(0, 12), randomBetween(1, 8), randomBetween(1, 3)));
        }

        return processes;
    }

    /**
     * Método que carga los valores del ejercicio 1 de l trabajo 3 de Sistemas Operativos
     *
     * @param model modelo
     */
    public static void ej1Tbj3(M_Main model) {
        model.addNewProcess("P1", 2, 3, 3);
        model.addNewProcess("P2", 3, 4, 2);
        model.addNewProcess("P3", 5, 2, 1);
        model.addNewProcess("P4", 0, 5, 2);
        model.addNewProcess("P5", 4, 6, 4);
    }

    /**
     * Método que carga N números de procesos con valores aleatorios
     *
     * @param model modelo
     */
    public static void randomProcess(M_Main model) {
        ArrayList<Process> processes = generateProcesses(randomBetween(5, 30));

        for (Process process : processes) {
            model.addNewProcess(process.getName(), process.getArrivalTime(), process.getCpuTime(), process.getPriority());
        }
    }

    public static int StringToInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return -1;
        }
    }

    public static int randomBetween(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
