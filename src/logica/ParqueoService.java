package logica;

import entidades.*;
import accesodatos.ArchivoDAO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ParqueoService {

    private ArchivoDAO dao = new ArchivoDAO();

    public void registrarIngreso(String placa, String tipo) throws Exception {

        if (placa == null || placa.isEmpty()) {
            throw new Exception("Placa requerida");
        }

        List<Registro> activos = dao.obtenerActivos();

        for (Registro r : activos) {
            if (r.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                throw new Exception("Vehículo ya está dentro");
            }
        }

        Vehiculo v = new Vehiculo(placa, tipo);
        Registro r = new Registro(v, LocalDateTime.now());

        dao.guardarIngreso(r);
    }

    public double registrarSalida(String placa) throws Exception {

        List<Registro> activos = dao.obtenerActivos();

        for (Registro r : activos) {
            if (r.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {

                r.setHoraSalida(LocalDateTime.now());

                double monto = calcularCobro(r);

                dao.registrarSalida(r, monto);

                return monto;
            }
        }

        throw new Exception("Vehículo no encontrado");
    }

    private double calcularCobro(Registro r) {
        long minutos = Duration.between(
                r.getHoraEntrada(),
                r.getHoraSalida()
        ).toMinutes();

        double horas = Math.ceil(minutos / 60.0);

        return horas * 700;
    }

    public List<Registro> obtenerActivos() {
        return dao.obtenerActivos();
    }

    public List<String> obtenerHistorial() {
        return dao.obtenerHistorial();
    }

    public void eliminarHistorial() {
        dao.limpiarHistorial();
    }
}