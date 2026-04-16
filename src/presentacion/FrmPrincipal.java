package presentacion;

import logica.ParqueoService;
import entidades.Registro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmPrincipal extends JFrame {

    private ParqueoService service = new ParqueoService();

    private JTable tablaActivos;
    private JTable tablaHistorial;

    public FrmPrincipal() {
        setTitle("Sistema de Parqueo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // PANEL SUPERIOR (BOTONES)
        JPanel panelTop = new JPanel();

        JButton btnIngreso = new JButton("Registrar Ingreso");
        JButton btnSalida = new JButton("Registrar Salida");
        JButton btnRefrescar = new JButton("Refrescar");
        JButton btnEliminar = new JButton("Eliminar Historial");

        panelTop.add(btnIngreso);
        panelTop.add(btnSalida);
        panelTop.add(btnRefrescar);
        panelTop.add(btnEliminar);

        add(panelTop, BorderLayout.NORTH);

        // TABLAS
        tablaActivos = new JTable();
        tablaHistorial = new JTable();

        JPanel panelCentro = new JPanel(new GridLayout(2, 1));

        panelCentro.add(new JScrollPane(tablaActivos));
        panelCentro.add(new JScrollPane(tablaHistorial));

        add(panelCentro, BorderLayout.CENTER);

        // EVENTOS
        btnIngreso.addActionListener(e -> new FrmIngreso().setVisible(true));
        btnSalida.addActionListener(e -> new FrmSalida().setVisible(true));

        btnRefrescar.addActionListener(e -> {
            cargarActivos();
            cargarHistorial();
        });

        btnEliminar.addActionListener(e -> {
            service.eliminarHistorial();
            cargarHistorial();
        });

        // CARGA INICIAL
        cargarActivos();
        cargarHistorial();
    }

    private void cargarActivos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Placa");
        modelo.addColumn("Tipo");
        modelo.addColumn("Hora Entrada");

        List<Registro> lista = service.obtenerActivos();

        for (Registro r : lista) {
            modelo.addRow(new Object[]{
                    r.getVehiculo().getPlaca(),
                    r.getVehiculo().getTipo(),
                    r.getHoraEntrada()
            });
        }

        tablaActivos.setModel(modelo);
    }

    private void cargarHistorial() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Placa");
        modelo.addColumn("Tipo");
        modelo.addColumn("Entrada");
        modelo.addColumn("Salida");
        modelo.addColumn("Monto");

        List<String> lista = service.obtenerHistorial();

        for (String linea : lista) {
            String[] datos = linea.split(",");

            modelo.addRow(new Object[]{
                    datos[0],
                    datos[1],
                    datos[2],
                    datos[3],
                    datos[4]
            });
        }

        tablaHistorial.setModel(modelo);
    }

    public static void main(String[] args) {
        new FrmPrincipal().setVisible(true);
    }
}