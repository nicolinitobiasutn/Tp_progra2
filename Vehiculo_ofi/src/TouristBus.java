public class TouristBus extends Vehiculo {
    private int cantidadPisos;

    public TouristBus(String patente, String marca, int capacidadPasajeros,
                              int anioFabricacion, int cantidadPisos) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        setCantidadPisos(cantidadPisos);
    }

    public int getCantidadPisos() { return cantidadPisos; }

    public void setCantidadPisos(int cantidadPisos) {
        if (cantidadPisos != 1 && cantidadPisos != 2) {
            System.out.println("El colectivo debe tener 1 o 2 pisos.");
        }
        this.cantidadPisos = cantidadPisos;
    }


    public void realizarServicio() {
        System.out.println("El colectivo " + getPatente()
                + " realiza una excursion turistica terrestre con " + cantidadPisos + " piso(s).");
    }


    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Cantidad de pisos: " + cantidadPisos);
    }
}
