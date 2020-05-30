package org.g2jl.controllers;

import org.g2jl.interfaces.I_Controller;
import org.g2jl.models.M_Main;
import org.g2jl.models.StateSimulation;
import org.g2jl.models.Process;
import org.g2jl.views.V_Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase controladora de la vista principal
 *
 * @author Juan Gahona
 * @version 20.5.30
 */
public class C_Main extends MouseAdapter implements I_Controller {
    private final V_Main view;
    private final M_Main model;

    private StateSimulation simulationState;

    public C_Main(V_Main view) {
        this.view = view;
        this.model = new M_Main(this);
        this.simulationState = StateSimulation.STOP;
    }

    public V_Main getView() {
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "TEMPO":
                if (model.runingSimulation()) {
                    if (view.getTempo().isRunning()) {
                        pauseTimer();
                        model.runningSimulation();
                        model.addBurst();
                        playTimer();
                        break;
                    }
                } else {
                    if (view.getTempo().isRunning()) {
                        model.showMessage("Simulación terminada");
                        view.enabledForm(false);
                        view.getBtnIniciar().setEnabled(false);
                        view.getBtnPause().setEnabled(false);
                    }
                    pauseTimer();
                }
                break;

            case "ADD_PROCESS":
                String nombre = view.getTxtName().getText();
                String arrivalTime = view.getTxtArrivalTime().getText();
                String cpuBurst = view.getTxtCpuTime().getText();
                String priority = view.getTxtPriority().getText();

                model.validateAndAddProcess(nombre, arrivalTime, cpuBurst, priority);

                updateTablesModel("CARGA");
                break;

            case "TEST_PROCESS":
                model.addNewProcess("P1", 2, 3, 3);
                model.addNewProcess("P2", 3, 4, 2);
                model.addNewProcess("P3", 5, 2, 1);
                model.addNewProcess("P4", 0, 5, 2);
                model.addNewProcess("P5", 4, 6, 4);
                updateTablesModel("CARGA");
                break;

            case "DELETE_PROCESS":
                int indice = procesoSeleccionado();
                if (indice != -1) {
                    model.getProcesses_carga().remove(indice);
                }
                updateTablesModel("CARGA");
                break;

            case "CLEAR_TABLE":
                model.setProcesses_carga(new ArrayList<>());
                model.setIdProcess(0);
                updateTablesModel("CARGA");
                break;

            case "PLAY_SIMULATION":
                if (model.haveProcess()) {
                    view.swichtButtons(false);
                    view.enabledForm(false);
                    playTimer();
                } else {
                    model.showMessage("Ingrese mínimo un proceso");
                }
                break;

            case "PAUSE_SIMULATION":
                pauseTimer();
                break;

            case "RESET_SIMULATION":
                model.resetValues();
                updateTablesModel("ALL");
                view.enabledForm(true);
                view.swichtButtons(true);
                break;

            default:
                model.showMessage(String.format("Acción %s no implementada", command));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable tabla = (JTable) e.getSource();

        if ("TABLA_CARGA".equals(tabla.getName())) {
            view.getBtnDeleteTable().setEnabled(true);
        }
    }

    public AtomicInteger getBurst() {
        return model.burst;
    }

    private void pauseTimer() {
        view.getTempo().stop();
    }

    private void playTimer() {
        view.getTempo().start();
    }

    public void updateTablesModel(String table) {
        DefaultTableModel tableModel;
        switch (table) {
            case "CARGA":
                tableModel = (DefaultTableModel) view.getTblCarga().getModel();
                clearTable(tableModel);
                for (Process process : model.getProcesses_carga()) {
                    Object[] row = new Object[]{process.getName(), process.getArrivalTime(), process.getCpuTime(), process.getPriority()};
                    tableModel.addRow(row);
                }
                break;
            case "COLA":
                tableModel = (DefaultTableModel) view.getTblCola().getModel();
                clearTable(tableModel);
                for (Process process : model.getProcesses_cola()) {
                    Object[] row = new Object[]{process.getName(), process.getArrivalTime(), process.getCpuTime(), process.getPriority()};
                    tableModel.addRow(row);
                }
                break;
            case "FINAL":
                tableModel = (DefaultTableModel) view.getTblFinal().getModel();
                clearTable(tableModel);
                for (Process process : model.getProcesses_final()) {
                    Object[] row = new Object[]{process.getName(), process.getWaitTime(), process.getReturnTime()};
                    tableModel.addRow(row);
                }
                break;
            case "ALL":
                updateTablesModel("CARGA");
                updateTablesModel("COLA");
                updateTablesModel("FINAL");
                break;
        }
    }

    private void clearTable(DefaultTableModel modelo) {
        int rows = modelo.getRowCount();
        if (rows > 0) {
            for (int i = rows - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }

    private void clearForm() {
        view.getTxtName().setText(String.format("P%d", model.getIdProcess()));
        view.getTxtArrivalTime().setText("");
        view.getTxtCpuTime().setText("");
        view.getTxtPriority().setText("");
    }

    private int procesoSeleccionado() {
        int row = this.view.getTblCarga().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) this.view.getTblCarga().getModel();
        return row;
    }

}
