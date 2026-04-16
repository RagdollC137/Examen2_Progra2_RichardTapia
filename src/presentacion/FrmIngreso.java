package presentacion;

import logica.ParqueoService;

import javax.swing.*;
import java.awt.*;

public class FrmIngreso extends JFrame {

    private ParqueoService service = new ParqueoService();

    private JTextField txtPlaca;
    private JComboBox<String> cbTipo;
    private JLabel lblMensaje;

    public FrmIngreso() {
        setTitle("Registro de Ingreso");
        setSize(300, 200);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2));

        add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        add(txtPlaca);

        add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"Carro", "Moto"});
        add(cbTipo);

        JButton btnGuardar = new JButton("Registrar");
        add(btnGuardar);

        lblMensaje = new JLabel("");
        add(lblMensaje);

        btnGuardar.addActionListener(e -> registrar());
    }

    private void registrar() {
        try {
            service.registrarIngreso(
                    txtPlaca.getText(),
                    cbTipo.getSelectedItem().toString()
            );
            lblMensaje.setText("Ingreso registrado");
        } catch (Exception e) {
            lblMensaje.setText(e.getMessage());
        }
    }
}