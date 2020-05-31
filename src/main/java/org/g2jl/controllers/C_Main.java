package org.g2jl.controllers;

import org.g2jl.models.M_Main;
import org.g2jl.models.Process;
import org.g2jl.utils.UtilData;
import org.g2jl.views.V_Main;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class C_Main extends MouseAdapter implements ActionListener {
    private final V_Main view;
    private M_Main model;

    public C_Main(V_Main view) {
        this.view = view;
        this.model = new M_Main(this);
    }

    public V_Main getView() {
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "TEMPO":
                if (model.isRunningSimulation()) {
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
                        model.presentResults();
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
                clearForm();
                break;

            case "TEST_PROCESS":
                int option = JOptionPane.showOptionDialog(this.view, "Seleccione entre cargar un ejercicio o un numero de procesos aleatorios", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Ejercicio", "Aleatorio"}, "Ejercicio");
                if (option == 0) {
                    UtilData.ej1Tbj3(model);
                } else if (option == 1) {
                    UtilData.randomProcess(model);
                }
                updateTablesModel("CARGA");
                clearForm();
                break;

            case "DELETE_PROCESS":
                int index = procesoSeleccionado();
                if (index != -1) {
                    model.getProcesses_carga().remove(index);
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
                    view.getBtnIniciar().setEnabled(false);
                    view.enabledForm(false);
                    model.setProcessesCargaCopy(model.getProcesses_carga());
                    playTimer();
                } else {
                    model.showMessage("Ingrese mínimo un proceso");
                }
                break;

            case "RESET_SIMULATION":
                model = new M_Main(this);
                view.getTempo().stop();
                view.getPnlCanvas().repaint();
                view.getPnlCanvas().setBurst(model.burst);
                updateTablesModel("ALL");
                view.enabledForm(true);
                view.getTxtName().setText("P1");
                view.getBtnIniciar().setEnabled(true);
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
            case "RESULTADOS":
                tableModel = (DefaultTableModel) view.getTblCarga().getModel();
                clearTable(tableModel);
                for (Process process : model.getProcessesCargaCopy()) {
                    Object[] row = new Object[]{process.getName(), process.getArrivalTime(), process.getCpuTime(), process.getPriority()};
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

    public void clearForm() {
        view.getTxtName().setText(String.format("P%d", model.getIdProcess()));
        view.getTxtArrivalTime().setText("");
        view.getTxtCpuTime().setText("");
        view.getTxtPriority().setText("");
    }

    private int procesoSeleccionado() {
        return this.view.getTblCarga().getSelectedRow();
    }

}
