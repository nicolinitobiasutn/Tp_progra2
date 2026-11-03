import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GestorVehiculos {
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    public void agregar(Vehiculo vehiculo) throws PatenteDuplicadaException {
        if (buscarPorPatente(vehiculo.getPatente()).isPresent()) {
            throw new PatenteDuplicadaException(vehiculo.getPatente());
        }
        vehiculos.add(vehiculo);
    }

    public Optional<Vehiculo> buscarPorPatente(String patente) {
        return vehiculos.stream()
                .filter(v -> v.getPatente().equalsIgnoreCase(patente.trim()))
                .findFirst();
    }

    public List<Vehiculo> listar() {
        return Collections.unmodifiableList(vehiculos);
    }

    public List<Vehiculo> filtrarPorCapacidadMayorA(int capacidad) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v.getCapacidadPasajeros() > capacidad) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    public List<Vehiculo> ordenadosPorAnioDescendente() {
        List<Vehiculo> copia = new ArrayList<>(vehiculos);
        copia.sort(Comparator.comparingInt(Vehiculo::getAnioFabricacion).reversed());
        return copia;
    }

    public List<Vehiculo> ordenadosPorCapacidadDescendente() {
        List<Vehiculo> copia = new ArrayList<>(vehiculos);
        copia.sort(Comparator.comparingInt(Vehiculo::getCapacidadPasajeros).reversed());
        return copia;
    }

    public void realizarServicios() {
        for (Vehiculo v : vehiculos) {
            v.realizarServicio();
        }
    }

    public boolean estaVacio() {
        return vehiculos.isEmpty();
    }
}
    

