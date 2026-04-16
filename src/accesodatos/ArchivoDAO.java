package accesodatos;

import entidades.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ArchivoDAO {

    private final String ACTIVOS = "activos.txt";
    private final String HISTORIAL = "historial.txt";

    public void guardarIngreso(Registro r) {
        try (FileWriter fw = new FileWriter(ACTIVOS, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(r.getVehiculo().getPlaca() + "," +
                     r.getVehiculo().getTipo() + "," +
                     r.getHoraEntrada());
            bw.newLine();

        } catch (Exception e) {}
    }

    public List<Registro> obtenerActivos() {
        List<Registro> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ACTIVOS))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                Vehiculo v = new Vehiculo(datos[0], datos[1]);
                Registro r = new Registro(v, LocalDateTime.parse(datos[2]));

                lista.add(r);
            }

        } catch (Exception e) {}

        return lista;
    }

    public void registrarSalida(Registro r, double monto) {

        List<Registro> activos = obtenerActivos();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACTIVOS))) {

            for (Registro reg : activos) {
                if (!reg.getVehiculo().getPlaca()
                        .equalsIgnoreCase(r.getVehiculo().getPlaca())) {

                    bw.write(reg.getVehiculo().getPlaca() + "," +
                             reg.getVehiculo().getTipo() + "," +
                             reg.getHoraEntrada());
                    bw.newLine();
                }
            }

        } catch (Exception e) {}

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORIAL, true))) {

            bw.write(r.getVehiculo().getPlaca() + "," +
                     r.getVehiculo().getTipo() + "," +
                     r.getHoraEntrada() + "," +
                     r.getHoraSalida() + "," +
                     monto);
            bw.newLine();

        } catch (Exception e) {}
    }

    public List<String> obtenerHistorial() {
        List<String> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(HISTORIAL))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }

        } catch (Exception e) {}

        return lista;
    }

    public void limpiarHistorial() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORIAL))) {
            bw.write("");
        } catch (Exception e) {}
    }
}