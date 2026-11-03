import java.time.Year;

public abstract class Vehiculo implements Servicio {
    private static final int ANIO_MINIMO = 1990;

    private final String patente;
    private final String marca;
    private final int capacidadPasajeros;
    private final int anioFabricacion;

    public Vehiculo(String patente, String marca, int capacidadPasajeros, int anioFabricacion)
            throws DatoInvalidoException {
        if (patente == null || patente.trim().isEmpty()) {
            throw new DatoInvalidoException("La patente no puede estar vacia.");
        }
        if (marca == null || marca.trim().isEmpty()) {
            throw new DatoInvalidoException("La marca no puede estar vacia.");
        }
        if (capacidadPasajeros <= 0) {
            throw new DatoInvalidoException("La capacidad debe ser mayor a cero.");
        }
        int anioActual = Year.now().getValue();
        if (anioFabricacion < ANIO_MINIMO || anioFabricacion > anioActual) {
            throw new DatoInvalidoException("El anio debe estar entre " + ANIO_MINIMO + " y " + anioActual + ". El vehiculo no pudo agregarse correctamente. ");
        }
        this.patente = patente.trim().toUpperCase();
        this.marca = marca.trim();
        this.capacidadPasajeros = capacidadPasajeros;
        this.anioFabricacion = anioFabricacion;
    }

    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    @Override
    public abstract void realizarServicio();

    public void mostrarDatos() {
        System.out.println("Tipo: " + getClass().getSimpleName());
        System.out.println("Patente: " + patente);
        System.out.println("Marca: " + marca);
        System.out.println("Capacidad: " + capacidadPasajeros + " pasajeros");
        System.out.println("Anioo de fabricacion: " + anioFabricacion);
    }

    @Override
    public String toString() {
        return "Patente: " + patente
                + " | Marca: " + marca
                + " | Capacidad: " + capacidadPasajeros
                + " | Anio: " + anioFabricacion;
    }
}
