package org.g2jl.models;

/**
 * Clase <b>Process</b> que ejemplifica un proceso de cpu a ejecutar.
 *
 * @author Juan Gahona
 * @version 20.5.10
 */
public class Process {
    private String id;
    private String name;
    private int arrivalTime;
    private int cpuTime;
    private int priority;

    private int waitTime;
    private int returnTime;

    /**
     * Constructor de clase
     *
     * @param id          identificador unico del proceso
     * @param name        nombre del proceso
     * @param arrivalTime tiempo de llegada del proceso
     * @param cpuTime     nro de rafagas de CPU que debe realizar
     * @param priority    prioridad con la cual debe ejecutarse
     */
    public Process(String id, String name, int arrivalTime, int cpuTime, int priority) {
        this.id = id;
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.priority = priority;

        this.waitTime = 0;
        this.returnTime = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(int cpuTime) {
        this.cpuTime = cpuTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
}
