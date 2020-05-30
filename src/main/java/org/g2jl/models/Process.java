package org.g2jl.models;

/**
 * Clase <b>Process</b> que ejemplifica un proceso de cpu a ejecutar.
 *
 * @author Juan Gahona
 * @version 20.5.30
 */
//implementamos interfaz
public class Process implements Comparable<Process> {
    //atributos
    private final int ID;
    private final String NAME;
    private final int ARRIVAL_TIME;
    private final int CPU_TIME;
    private final int PRIORITY;

    //instanciamos clase que otorga estados del proceso
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

    //devuelve ID
    public int getId() {
        return ID;
    }
    //devuelve nombre del proceso
    public String getName() {
        return NAME;
    }
    //devuelve tiempo de llegada
    public int getArrivalTime() {
        return ARRIVAL_TIME;
    }
    //devuelve ráfagas de CPU
    public int getCpuTime() {
        return CPU_TIME;
    }
    //devuelve prioridad
    public int getPriority() {
        return PRIORITY;
    }
    //devuelve tiempo de espera
    public int getWaitTime() {
        return waitTime;
    }
    //otorgamos valor para el tiempo de espera
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }
    //otorgamos valor para el tiempo de retorno
    public int getReturnTime() {
        return returnTime;
    }
    /**
     * otorgamos valor para el tiempo de retorno
     * @param returnTime
     */
    public void setReturnTime(int returnTime) {
        this.returnTime = returnTime;
    }
    //devuelve tiempo inicial
    public int getInitTime() {
        return initTime;
    }
    //método booleano que devuelve el número de ráfaga diferente de 0
    public boolean moreBurst() {
        return burstNum != 0;
    }
    //método que evalua si el número de ráfaga es igual a 0 para determinar su estado como terminado
    public void runBurst() {
        burstNum -= 1;
        if (burstNum == 0) {
            state = StateProcess.TERMINADO;
        }
    }
    //método booleano que devuelve el estado preparado
    public boolean isReady() {
        return state == StateProcess.PREPARADO;
    }
    //método booleano que devuelve el estado esperando
    public boolean isWaiting() {
        return state == StateProcess.ESPERANDO;
    }
    //método booleano que devuelve el estado procesando
    public boolean isRunning() {
        return state == StateProcess.PROCESANDO;
    }
    //método booleano que devuelve el estado terminado
    public boolean isFinished() {
        return state == StateProcess.TERMINADO;
    }
    //método que se le otorga un estado (Esperando) cuando se espera un proceso
    public void waitProcess() {
        this.state = StateProcess.ESPERANDO;
    }
    /**
     * método que se le otroga una ráfaga y establece su estado a procesando, un tiempo inicial igual a la ráfaga y
     * el número de ráfaga
     * @param burst
     */
    public void runProcess(int burst) {
        this.state = StateProcess.PROCESANDO;
        this.initTime = burst;
        this.burstNum = CPU_TIME;
    }

    /**
     * método que otorga un número de ráfaga y lo iguala al tiempo de retorno del proceso, seguido de la resta entre
     * tiempo de retorno y la suma entre las ráfagas del CPU y el tiempo de arrivo
     * @param burst
     */
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
