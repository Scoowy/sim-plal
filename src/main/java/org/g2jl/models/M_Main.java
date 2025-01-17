package org.g2jl.models;

import org.g2jl.controllers.C_Main;
import org.g2jl.utils.UtilData;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Modelo de la vista Main
 *
 * @author Juan Gahona
 * @version 20.5.30
 */
public class M_Main {
    private final C_Main controller;

    private ArrayList<Process> processes_carga;
    private ArrayList<Process> processes_cola;
    private ArrayList<Process> processes_final;

    private ArrayList<Process> processesCargaCopy;

    private int idProcess;

    public AtomicInteger burst;

    private boolean emptyCola;
    private boolean emptyCarga;


    public M_Main(C_Main controller) {
        this.controller = controller;

        this.processes_carga = new ArrayList<>();
        this.processes_cola = new ArrayList<>();
        this.processes_final = new ArrayList<>();

        this.idProcess = 1;

        this.burst = new AtomicInteger(0);
    }

    public int getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(int idProcess) {
        this.idProcess = idProcess;
    }

    public ArrayList<Process> getProcessesCargaCopy() {
        return this.processesCargaCopy;
    }

    public void setProcessesCargaCopy(ArrayList<Process> processesC) {
        this.processesCargaCopy = (ArrayList<Process>) processesC.clone();
    }

    public void validateAndAddProcess(String nombre, String arrivalTime, String cpuBurst, String priority) {
        int at = UtilData.StringToInt(arrivalTime);
        int cb = UtilData.StringToInt(cpuBurst);
        int py = UtilData.StringToInt(priority);

        if (at == -1) {
            showMessage(String.format("\"%s\" no es un numero", arrivalTime));
        } else if (cb == -1) {
            showMessage(String.format("\"%s\" no es un numero", cpuBurst));
        } else if (py == -1) {
            showMessage(String.format("\"%s\" no es un numero", priority));
        } else {
            addNewProcess(nombre, at, cb, py);
            controller.clearForm();
        }

    }

    public void addNewProcess(String nombre, int arrivalTime, int cpuBurst, int priority) {
        Process newProcess = new Process(idProcess, nombre, arrivalTime, cpuBurst, priority);
        processes_carga.add(newProcess);
        idProcess += 1;
    }

    public void deleteProcess(int idProcess, String lista) {
        switch (lista) {
            case "CARGA":
                processes_carga.removeIf(process -> process.getId() == idProcess);
                break;
            case "COLA":
                processes_cola.removeIf(process -> process.getId() == idProcess);
                break;
            case "FINAL":
                processes_final.removeIf(process -> process.getId() == idProcess);
        }
    }

    public boolean moveProcessesCargaToCola() {
        ArrayList<Integer> ids = new ArrayList<>();
        if (!processes_carga.isEmpty()) {
            for (Process process : processes_carga) {
                if (process.getArrivalTime() == burst.get()) {
                    ids.add(process.getId());
                }
            }
            for (Integer id : ids) {
                moveProcessCargaToCola(id);
            }
        } else {
            emptyCarga = true;
        }
        return !ids.isEmpty();
    }

    public void moveProcessCargaToCola(int idProcess) {
        for (Process process : processes_carga) {
            if (process.getId() == idProcess) {
                process.waitProcess();
                processes_cola.add(process);
                emptyCola = false;
            }
        }
        deleteProcess(idProcess, "CARGA");
        controller.updateTablesModel("CARGA");
        controller.updateTablesModel("COLA");
    }

    public void moveProcessColaToFinal(int idProcess) {
        for (Process process : processes_cola) {
            if (process.getId() == idProcess) {
                process.endProcess(burst.get());
                processes_final.add(process);
            }
        }
        deleteProcess(idProcess, "COLA");
        controller.updateTablesModel("COLA");
        controller.updateTablesModel("FINAL");
    }

    public double[] calculateAverages() {
        double[] totales = new double[2];
        for (Process process : processes_final) {
            totales[0] += process.getWaitTime();
            totales[1] += process.getReturnTime();
        }
        totales[0] /= processes_final.size();
        totales[1] /= processes_final.size();

        return totales;
    }

    public void sortSJF() {
        processes_cola.sort(Process::compareTo);
    }

    public void processBurst(int idProcessRun) {
        for (Process process : processes_cola) {
            if (process.getId() == idProcessRun) {
                process.runBurst();
            }
        }
    }

    public Process nextProcessRunning() {
        // Comprueba si existen mas elementos en carga
        if (!processes_cola.isEmpty()) {
            // De existir escoge el primero y cambia su estado a PROCESANDO
            processes_cola.get(0).runProcess(burst.get());
            return processes_cola.get(0);
        } else {
            emptyCola = true;
            return null;
        }
    }

    public Process haveProcessRunning() {
        for (Process process : processes_cola) {
            if (process.isRunning()) {
                return process;
            }
        }
        return null;
    }

    public void runningSimulation() {
        // Movemos los procesos de carga a cola según el burst
        if (moveProcessesCargaToCola()) {
            // Si hubo elementos movidos se reordena la lista.
            sortSJF();
        }

        // Comprobamos si no existen mas procesos en carga
        emptyCarga = processes_carga.isEmpty();

        // Obtenemos el id del proceso en ejecución de existir si no -1
        Process procRunning = haveProcessRunning();
        // Comprobamos si existe un proceso
        if (procRunning != null) {
            // Procesamos únicamente un burst de este proceso
            processBurst(procRunning.getId());
            // Si encuentra procesos terminados los mueve de lista
            if (moveTerminatedProcess()) {
                emptyCola = processes_cola.isEmpty();
                // Busca el siguiente de existir
                procRunning = nextProcessRunning();
            }
        } else {
            // Busca el siguiente de existir
            procRunning = nextProcessRunning();
        }
        controller.getView().getPnlCanvas().processListToCellList(processes_final, procRunning);
        controller.getView().getPnlCanvas().repaint();
    }

    private boolean moveTerminatedProcess() {
        ArrayList<Integer> ids = new ArrayList<>();
        // Obtenemos los ids de los procesos terminados
        for (Process process : processes_cola) {
            if (process.isFinished()) {
                ids.add(process.getId());
            }
        }
        // De existir realizamos el cambio de listas y retronamos true
        if (!ids.isEmpty()) {
            for (Integer id : ids) {
                moveProcessColaToFinal(id);
            }
            return true;
        }
        // Caso contrario false
        return false;
    }

    public boolean haveProcess() {
        return !processes_carga.isEmpty();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(controller.getView(), message, "Aviso", JOptionPane.ERROR_MESSAGE);
    }

    public ArrayList<Process> getProcesses_carga() {
        return processes_carga;
    }

    public void setProcesses_carga(ArrayList<Process> processes_carga) {
        this.processes_carga = processes_carga;
    }

    public ArrayList<Process> getProcesses_cola() {
        return processes_cola;
    }

    public ArrayList<Process> getProcesses_final() {
        return processes_final;
    }

    public boolean isRunningSimulation() {
        return !(emptyCarga && emptyCola);
    }

    public void addBurst() {
        burst.incrementAndGet();
    }

    public void presentResults() {
        DefaultTableModel tModel = (DefaultTableModel) controller.getView().getTblFinal().getModel();
        double[] totales = calculateAverages();
        Object[] row = new Object[]{"TOTALES", totales[0], totales[1]};
        tModel.addRow(row);

        controller.updateTablesModel("RESULTADOS");
    }
}
