package org.g2jl.models;

/**
 * Clase <b>Process</b> que ejemplifica un proceso de cpu a ejecutar.
 *
 * @author Juan Gahona
 * @version 20.5.15
 */
public class Process {
    private final int ID;
    private final String NAME;
    private final int ARRIVAL_TIME;
    private final int CPU_TIME;
    private final int PRIORITY;

    private int waitTime;
    private int returnTime;
    private int burstNum;

    /**
     * Constructor de clase
     *
     * @param ID           identificador unico del proceso
     * @param NAME         nombre del proceso
     * @param ARRIVAL_TIME tiempo de llegada del proceso
     * @param CPU_TIME     nro de rafagas de CPU que debe realizar
     * @param PRIORITY     prioridad con la cual debe ejecutarse
     */
    public Process(int ID, String NAME, int ARRIVAL_TIME, int CPU_TIME, int PRIORITY) {
        this.ID = ID;
        this.NAME = NAME;
        this.ARRIVAL_TIME = ARRIVAL_TIME;
        this.CPU_TIME = CPU_TIME;
        this.PRIORITY = PRIORITY;

        this.waitTime = 0;
        this.returnTime = 0;
        this.burstNum = 0;
    }

    public int getId() { return ID; }

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

    public int getBurstNum() { return burstNum; }

    public void setBurstNum(int burstNum) { this.burstNum = burstNum; }
}
