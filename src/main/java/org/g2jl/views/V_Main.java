package org.g2jl.views;

import net.miginfocom.swing.MigLayout;
import org.g2jl.controllers.C_Main;
import org.g2jl.interfaces.I_View;
import org.g2jl.models.DiagramaGant;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTextField;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Container;
import java.awt.Dimension;

/**
 * Vista principal del programa, se usa la librería de MigLayout para la gestión de los componentes en la interfaz.
 *
 * @author Juan Gahona
 * @version 20.5.30
 */
public class V_Main extends JXFrame implements I_View {

    // Declaración de los elementos que contendrá ala venta principal
    private JScrollPane scrollCanvas;
    private DiagramaGant pnlCanvas;
    private JXPanel pnlForm;
    private JXPanel pnlButtons;

    // === Labels ===
    private JXLabel lblName;
    private JXLabel lblArrivalTime;
    private JXLabel lblCpuTime;
    private JXLabel lblPriority;

    // === TextFields de SwingX ===
    private JXTextField txtName;
    private JXTextField txtArrivalTime;
    private JXTextField txtCpuTime;
    private JXTextField txtPriority;

    // === JScrollPanes ===
    private JScrollPane scrollCola;
    private JScrollPane scrollFinal;
    private JScrollPane scrollCarga;

    // === JTables de SwingX ===
    private JXTable tblCola;
    private JXTable tblFinal;
    private JXTable tblCarga;

    // === JButtons de SwingX ===
    private JXButton btnFakeData;
    private JXButton btnAddForm;
    private JXButton btnClearTable;
    private JXButton btnDeleteTable;
    private JXButton btnReset;
    private JXButton btnIniciar;

    // === Controlador de la venta principal ===
    private C_Main controller;

    // === Timer global encargado de la secuencia de las ráfagas ===
    private Timer tempo;

    /**
     * Constructor de clase
     */
    public V_Main() {
        initComponents();
        addController();
        txtName.setEditable(false);
        txtName.setText("P0");
    }

    // === Getters y Setters ===
    public JXButton getBtnIniciar() {
        return btnIniciar;
    }

    public DiagramaGant getPnlCanvas() {
        return pnlCanvas;
    }

    public JXTable getTblCarga() {
        return tblCarga;
    }

    public JXTable getTblCola() {
        return tblCola;
    }

    public JXTable getTblFinal() {
        return tblFinal;
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

    public JXButton getBtnDeleteTable() {
        return btnDeleteTable;
    }

    public Timer getTempo() {
        return tempo;
    }

    /**
     * Método encargado de inicializar los componentes de la vista
     */
    private void initComponents() {
        tempo = new Timer(750, null); // Cada 750 ms se lanzara un evento

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
        btnIniciar = new JXButton();

        //======== this ========
        controller = new C_Main(this);
        pnlCanvas.setBurst(controller.getBurst());
        setTitle("Sistemas Operativos | Short Job First");
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
            modelDefaultTable(tblCola, scrollCola);
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
                final Class<?>[] columnTypes = new Class<?>[]{
                        String.class, Double.class, Double.class
                };
                final boolean[] columnEditable = new boolean[]{
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
            modelDefaultTable(tblCarga, scrollCarga);
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

            //---- btnIniciar ----
            btnIniciar.setText("Iniciar");
            pnlButtons.add(btnIniciar, "cell 0 0");
        }
        contentPane.add(pnlButtons, "pad 0 50% 0 0,south,gapx null 15,gapy null 15");
        setSize(1296, 800);
        setLocationRelativeTo(null);
    }

    /**
     * Método que establece un DefaultTableModel y un JScrollPane, para las dos tablas de carga y cola.
     *
     * @param table  tabla
     * @param scroll scroll donde se ubica la tabla
     */
    private void modelDefaultTable(JXTable table, JScrollPane scroll) {
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                },
                new String[]{
                        "Proceso", "Tiempo Llegada", "R\u00e1faga CPU", "Prioridad"
                }
        ) {
            final Class<?>[] columnTypes = new Class<?>[]{
                    String.class, Integer.class, Integer.class, Integer.class
            };
            final boolean[] columnEditable = new boolean[]{
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
        scroll.setViewportView(table);
    }

    /**
     * Método que engloba los elementos del formulario de procesos
     * para habilitar o deshabilitarlos
     *
     * @param enabled true o false
     */
    public void enabledForm(boolean enabled) {
        txtName.setEnabled(enabled);
        txtArrivalTime.setEnabled(enabled);
        txtCpuTime.setEnabled(enabled);
        txtPriority.setEnabled(enabled);

        btnAddForm.setEnabled(enabled);
        btnFakeData.setEnabled(enabled);
    }

    /**
     * Método encargado de asignar el controlador como un Listener de eventos
     * para los botones y la tabla carga.
     * Formato:
     * Establecer un nombre para el comando            setActionCommand([Nombre del comando])
     * Establecer el controlador que los escuchara     addActionListener([Controlador])
     */
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
