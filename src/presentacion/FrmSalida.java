package presentacion;

import logica.ParqueoService;

import javax.swing.*;
import java.awt.*;

public class FrmSalida extends JFrame {

    private ParqueoService service = new ParqueoService();

    private JTextField txtPlaca;
    private JLabel lblMensaje;

    public FrmSalida() {
        setTitle("Registro de Salida");
        setSize(300, 150);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 2));

        add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        add(txtPlaca);

        JButton btnSalida = new JButton("Registrar salida");
        add(btnSalida);

        lblMensaje = new JLabel("");
        add(lblMensaje);

        btnSalida.addActionListener(e -> registrarSalida());
    }

    private void registrarSalida() {
        try {
            double monto = service.registrarSalida(txtPlaca.getText());
            lblMensaje.setText("Monto: ₡" + monto);
        } catch (Exception e) {
            lblMensaje.setText(e.getMessage());
        }
    }
}