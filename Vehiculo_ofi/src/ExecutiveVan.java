public class ExecutiveVan extends Vehiculo {
    private boolean aireAcondicionado;

    public ExecutiveVan(String patente, String marca, int capacidadPasajeros, int anioFabricacion, boolean aireAcondicionado) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean tieneAireAcondicionado() {
        return aireAcondicionado;
    }


    public void realizarServicio() {
        System.out.println("La van ejecutiva " + getPatente()
                + " realiza un traslado turístico. Aire acondicionado: "
                + (aireAcondicionado ? "sí" : "no") + ".");
    }

    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Aire acondicionado: " + (aireAcondicionado ? "Sí" : "No"));
    }
}
