package org.g2jl.views;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlContrastIJTheme;
import net.miginfocom.swing.MigLayout;
import org.g2jl.controllers.C_Main;
import org.g2jl.interfaces.I_View;
import org.g2jl.models.DiagramaGant;
import org.jdesktop.swingx.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author Juan Gahona
 * @version 20.5.30
 */
public class V_Main extends JXFrame implements I_View {

    private JScrollPane scrollCanvas;
    private DiagramaGant pnlCanvas;
    private JXPanel pnlForm;
    private JXPanel pnlButtons;

    private JXLabel lblName;
    private JXLabel lblArrivalTime;
    private JXLabel lblCpuTime;
    private JXLabel lblPriority;

    private JXTextField txtName;
    private JXTextField txtArrivalTime;
    private JXTextField txtCpuTime;
    private JXTextField txtPriority;

    private JScrollPane scrollCola;
    private JScrollPane scrollFinal;
    private JScrollPane scrollCarga;

    private JXTable tblCola;
    private JXTable tblFinal;
    private JXTable tblCarga;

    private JXButton btnFakeData;
    private JXButton btnAddForm;
    private JXButton btnClearTable;
    private JXButton btnDeleteTable;
    private JXButton btnReset;
    private JXButton btnPause;
    private JXButton btnIniciar;

    private C_Main controller;

    private Timer tempo;

    public V_Main() {
        try {
            UIManager.setLookAndFeel(new FlatLightOwlContrastIJTheme());
        } catch (Exception ex) {
            System.err.println("Fallo al cargar el tema.");
        }
        initComponents();
        addController();
        txtName.setEditable(false);
        txtName.setText("P1");
    }

    public JXButton getBtnPause() {
        return btnPause;
    }

    public JXButton getBtnReset() {
        return btnReset;
    }

    public JXPanel getPnlButtons() {
        return pnlButtons;
    }

    public JXButton getBtnIniciar() {
        return btnIniciar;
    }

    public DiagramaGant getPnlCanvas() {
        return pnlCanvas;
    }

    public JScrollPane getScrollCarga() {
        return scrollCarga;
    }

    public JXTable getTblCarga() {
        return tblCarga;
    }

    public JScrollPane getScrollCola() {
        return scrollCola;
    }

    public JXTable getTblCola() {
        return tblCola;
    }

    public JScrollPane getScrollFinal() {
        return scrollFinal;
    }

    public JXTable getTblFinal() {
        return tblFinal;
    }

    public JXPanel getPnlForm() {
        return pnlForm;
    }

    public JXLabel getLblName() {
        return lblName;
    }

    public JXLabel getLblArrivalTime() {
        return lblArrivalTime;
    }

    public JXLabel getLblCpuTime() {
        return lblCpuTime;
    }

    public JXLabel getLblPriority() {
        return lblPriority;
    }

    public JXTextField getTxtName() {
        return txtName;
    }

    public JXTextField getTxtArrivalTime() {
        return txtArrivalTime;
    }

    public JXTextField getTxtCpuTime() {
        return txtCpuTime;
    }

    public JXTextField getTxtPriority() {
        return txtPriority;
    }

    public JXButton getBtnAddForm() {
        return btnAddForm;
    }

    public JXButton getBtnFakeData() {
        return btnFakeData;
    }

    public JXButton getBtnClearTable() {
        return btnClearTable;
    }

    public JXButton getBtnDeleteTable() {
        return btnDeleteTable;
    }

    public Timer getTempo() {
        return tempo;
    }

    private void initComponents() {
        tempo = new Timer(750, null);

        scrollCanvas = new JScrollPane();
        pnlCanvas = new DiagramaGant();
        pnlForm = new JXPanel();
        pnlButtons = new JXPanel();

        lblName = new JXLabel();
        lblArrivalTime = new JXLabel();
        lblCpuTime = new JXLabel();
        lblPriority = new JXLabel();

        txtName = new JXTextField();
        txtArrivalTime = new JXTextField();
        txtCpuTime = new JXTextField();
        txtPriority = new JXTextField();

        scrollCola = new JScrollPane();
        scrollCarga = new JScrollPane();
        scrollFinal = new JScrollPane();

        tblCola = new JXTable();
        tblCarga = new JXTable();
        tblFinal = new JXTable();

        btnFakeData = new JXButton();
        btnAddForm = new JXButton();
        btnClearTable = new JXButton();
        btnDeleteTable = new JXButton();
        btnReset = new JXButton();
        btnPause = new JXButton();
        btnIniciar = new JXButton();

        //======== this ========
        controller = new C_Main(this);
        pnlCanvas.setBurst(controller.getBurst());
        setTitle("Ventana principal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1296, 800));
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "novisualpadding,hidemode 3",
                // columns
                "20px[grow, center, 33%]" +
                        "[grow, center, 33%]" +
                        "[grow, center, 33%]20px",
                // rows
                "[grow,center]" +
                        "[grow,center]" +
                        "[]" +
                        "[]"));

        //====== scrollCanvas =======
        scrollCanvas.setViewportView(pnlCanvas);
        scrollCanvas.setBorder(new TitledBorder(null, "Diagrama de Gant", TitledBorder.LEFT, TitledBorder.TOP));
        scrollCanvas.createHorizontalScrollBar();
        //======== pnlCanvas ========
        contentPane.add(scrollCanvas, "pad 15 15 0 -15,north,width 800:800:100%,height 250!");

        //======== formProcess ========
        {
            pnlForm.setBorder(new TitledBorder("Nuevo Proceso"));
            pnlForm.setLayout(new MigLayout(
                    "fill,insets panel,hidemode 3,alignx center",
                    // columns
                    "[30%]" +
                            "[40%]" +
                            "[30%]",
                    // rows
                    "[]" +
                            "[]" +
                            "[]" +
                            "[]"));

            //---- lblName ----
            lblName.setText("Nombre:");
            pnlForm.add(lblName, "cell 0 1");
            pnlForm.add(txtName, "cell 1 1,width 100%");

            //---- btnAddForm ----
            btnAddForm.setText("Añadir");
            pnlForm.add(btnAddForm, "cell 2 1");

            //---- lblArrivalTime ----
            lblArrivalTime.setText("Tiempo llegada:");
            pnlForm.add(lblArrivalTime, "cell 0 2");
            pnlForm.add(txtArrivalTime, "cell 1 2,width 100%");

            //---- btnClearForm ----
            btnFakeData.setText("Prueba");
            pnlForm.add(btnFakeData, "cell 2 2");

            //---- lblCpuTime ----
            lblCpuTime.setText("Ráfaga CPU:");
            pnlForm.add(lblCpuTime, "cell 0 3");
            pnlForm.add(txtCpuTime, "cell 1 3,width 100%");

            //---- lblPriority ----
            lblPriority.setText("Prioridad:");
            pnlForm.add(lblPriority, "cell 0 4");
            pnlForm.add(txtPriority, "cell 1 4,width 100%");
        }
        contentPane.add(pnlForm, "cell 0 0,width 33%");

        //======== scrollCola ========
        {
            scrollCola.setBorder(new TitledBorder("Cola"));

            //---- tblCola ----
            tblCola.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null},
                    },
                    new String[]{
                            "Proceso", "Tiempo Llegada", "R\u00e1faga CPU", "Prioridad"
                    }
            ) {
                Class<?>[] columnTypes = new Class<?>[]{
                        String.class, Integer.class, Integer.class, Integer.class
                };
                boolean[] columnEditable = new boolean[]{
                        false, false, false, false
                };

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            scrollCola.setViewportView(tblCola);
        }
        contentPane.add(scrollCola, "cell 1 0 1 3,width 100%,height 100%");

        //======== scrollFinal ========
        {
            scrollFinal.setBorder(new TitledBorder("Finalizados"));

            //---- tblFinal ----
            tblFinal.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null},
                    },
                    new String[]{
                            "Proceso", "Tiempo Espera", "Tiempo Retorno"
                    }
            ) {
                Class<?>[] columnTypes = new Class<?>[]{
                        String.class, Double.class, Double.class
                };
                boolean[] columnEditable = new boolean[]{
                        false, false, false
                };

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            scrollFinal.setViewportView(tblFinal);
        }
        contentPane.add(scrollFinal, "cell 2 0 1 3,width 100%,height 100%");

        //======== scrollCarga ========
        {
            scrollCarga.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollCarga.setViewportBorder(null);
            scrollCarga.setBorder(new TitledBorder("Carga"));

            //---- tblCarga ----
            tblCarga.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null},
                    },
                    new String[]{
                            "Proceso", "Tiempo Llegada", "R\u00e1faga CPU", "Prioridad"
                    }
            ) {
                Class<?>[] columnTypes = new Class<?>[]{
                        String.class, Integer.class, Integer.class, Integer.class
                };

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
            });
            scrollCarga.setViewportView(tblCarga);
        }
        contentPane.add(scrollCarga, "cell 0 1,width 100%,height 100%");

        //---- btnClearTable ----
        btnClearTable.setText("Limpiar");
        contentPane.add(btnClearTable, "cell 0 2");

        //---- btnDeleteTable ----
        btnDeleteTable.setText("Eliminar");
        contentPane.add(btnDeleteTable, "cell 0 2");

        //======== pnlButtons ========
        {
            pnlButtons.setLayout(new MigLayout(
                    "fill,hidemode 3,align right center,gapy 50",
                    // columns
                    "[fill]",
                    // rows
                    "[]"));

            //---- btnReset ----
            btnReset.setText("Reset");
            pnlButtons.add(btnReset, "cell 0 0");

            //---- btnMensaje ----
            btnPause.setText("Mensaje");
            btnPause.addActionListener(controller);
            pnlButtons.add(btnPause, "cell 0 0");

            //---- btnIniciar ----
            btnIniciar.setText("Iniciar");
            pnlButtons.add(btnIniciar, "cell 0 0");
        }
        contentPane.add(pnlButtons, "pad 0 50% 0 0,south,gapx null 15,gapy null 15");
        setSize(1296, 800);
        setLocationRelativeTo(null);
    }

    public void enabledForm(boolean enabled){
        txtName.setEnabled(enabled);
        txtArrivalTime.setEnabled(enabled);
        txtCpuTime.setEnabled(enabled);
        txtPriority.setEnabled(enabled);

        btnAddForm.setEnabled(enabled);
        btnFakeData.setEnabled(enabled);
    }

    public void swichtButtons(boolean enabled) {
        btnIniciar.setEnabled(enabled);
        btnPause.setEnabled(!enabled);
    }

    @Override
    public void addController() {
        btnAddForm.setActionCommand("ADD_PROCESS");
        btnAddForm.addActionListener(controller);

        btnFakeData.setActionCommand("TEST_PROCESS");
        btnFakeData.addActionListener(controller);

        btnClearTable.setActionCommand("CLEAR_TABLE");
        btnClearTable.addActionListener(controller);

        btnDeleteTable.setActionCommand("DELETE_PROCESS");
        btnDeleteTable.addActionListener(controller);

        btnPause.setActionCommand("PAUSE_SIMULATION");
        btnPause.addActionListener(controller);

        btnIniciar.setActionCommand("PLAY_SIMULATION");
        btnIniciar.addActionListener(controller);

        btnReset.setActionCommand("RESET_SIMULATION");
        btnReset.addActionListener(controller);

        tempo.setActionCommand("TEMPO");
        tempo.addActionListener(controller);

        tblCarga.setName("TABLA_CARGA");
        tblCarga.addMouseListener(controller);
    }

}
