public class ExecutiveVan extends Vehiculo {
    private final boolean aireAcondicionado;

    public ExecutiveVan(String patente, String marca, int capacidadPasajeros,
                        int anioFabricacion, boolean aireAcondicionado) throws DatoInvalidoException {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean tieneAireAcondicionado() {
        return aireAcondicionado;
    }

    @Override
    public void realizarServicio() {
        System.out.println("La van ejecutiva " + getPatente()
                + " realiza un traslado turistico. Aire acondicionado: "
                + (aireAcondicionado ? "si" : "no") + ".");
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Aire acondicionado: " + (aireAcondicionado ? "Si" : "No"));
    }
}

