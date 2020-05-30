package org.g2jl.models;

/**
 * Clase <b>Process</b> que ejemplifica un proceso de cpu a ejecutar.
 *
 * @author Juan Gahona
 * @version 20.5.30
 */
public class Process implements Comparable<Process> {
    private final int ID;
    private final String NAME;
    private final int ARRIVAL_TIME;
    private final int CPU_TIME;
    private final int PRIORITY;

    private StateProcess state;

    private int waitTime;
    private int returnTime;
    private int burstNum;
    private int initTime;

    /**
     * Constructor de clase
     *
     * @param ID           identificador único del proceso
     * @param NAME         nombre del proceso
     * @param ARRIVAL_TIME tiempo de llegada del proceso
     * @param CPU_TIME     nro de ráfagas de CPU que debe realizar
     * @param PRIORITY     prioridad con la cual debe ejecutarse
     */
    public Process(int ID, String NAME, int ARRIVAL_TIME, int CPU_TIME, int PRIORITY) {
        this.ID = ID;
        this.NAME = NAME;
        this.ARRIVAL_TIME = ARRIVAL_TIME;
        this.CPU_TIME = CPU_TIME;
        this.PRIORITY = PRIORITY;

        this.state = StateProcess.PREPARADO;

        this.waitTime = 0;
        this.initTime = 0;
        this.returnTime = 0;
        this.burstNum = 0;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return NAME;
    }

    public int getArrivalTime() {
        return ARRIVAL_TIME;
    }

    public int getCpuTime() {
        return CPU_TIME;
    }

    public int getPriority() {
        return PRIORITY;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(int returnTime) {
        this.returnTime = returnTime;
    }

    public int getInitTime() {
        return initTime;
    }

    public boolean moreBurst() {
        return burstNum != 0;
    }

    public void runBurst() {
        burstNum -= 1;
        if (burstNum == 0) {
            state = StateProcess.TERMINADO;
        }
    }

    public boolean isReady() {
        return state == StateProcess.PREPARADO;
    }

    public boolean isWaiting() {
        return state == StateProcess.ESPERANDO;
    }

    public boolean isRunning() {
        return state == StateProcess.PROCESANDO;
    }

    public boolean isFinished() {
        return state == StateProcess.TERMINADO;
    }

    public void waitProcess() {
        this.state = StateProcess.ESPERANDO;
    }

    public void runProcess(int burst) {
        this.state = StateProcess.PROCESANDO;
        this.initTime = burst;
        this.burstNum = CPU_TIME;
    }

    public void endProcess(int burst) {
        this.returnTime = burst;
        this.waitTime = returnTime -(this.CPU_TIME + this.ARRIVAL_TIME);
    }

    /**
     * Método encargado de comparar dos Procesos por el algoritmo de SJF no apropiativo.
     * Primero tiene en cuenta el menor tiempo de CPU, de ser iguales se compara
     * usando el algoritmo de FIFO, de este ser igual se tiene en cuenta el orden en
     * que fueron ingresados.
     *
     * @param pc proceso con el cual se compara
     * @return -1 si es menor, 1 si es mayor y 0 si son iguales
     */
    public int compareTo(Process pc) {
        if (state == StateProcess.PROCESANDO) {
            return 1;
        } else {
            if (CPU_TIME > pc.getCpuTime()) {
                return 1;
            } else if (CPU_TIME < pc.getCpuTime()) {
                return -1;
            } else {
                if (ARRIVAL_TIME > pc.getArrivalTime()) {
                    return 1;
                } else if (ARRIVAL_TIME < pc.getArrivalTime()) {
                    return -1;
                } else {
                    return Integer.compare(ID, pc.getId());
                }
            }
        }
    }

}
