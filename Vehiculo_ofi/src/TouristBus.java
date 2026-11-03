public class TouristBus extends Vehiculo {
    private final int cantidadPisos;

    public TouristBus(String patente, String marca, int capacidadPasajeros,
                      int anioFabricacion, int cantidadPisos) throws DatoInvalidoException {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        if (cantidadPisos != 1 && cantidadPisos != 2) {
            throw new DatoInvalidoException("El colectivo debe tener 1 o 2 pisos.");
        }
        this.cantidadPisos = cantidadPisos;
    }

    public int getCantidadPisos() {
        return cantidadPisos;
    }

    @Override
    public void realizarServicio() {
        System.out.println("El colectivo " + getPatente()
                + " realiza una excursion turistica terrestre con " + cantidadPisos + " piso(s).");
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Cantidad de pisos: " + cantidadPisos);
    }
}
