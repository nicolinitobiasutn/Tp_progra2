public class TouristBoat extends Vehiculo {
    private final TipoMotor tipoMotor;

    public TouristBoat(String patente, String marca, int capacidadPasajeros,
                       int anioFabricacion, TipoMotor tipoMotor) throws DatoInvalidoException {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        if (tipoMotor == null) {
            throw new DatoInvalidoException("Debe indicar un tipo de motor valido.");
        }
        this.tipoMotor = tipoMotor;
    }

    public TipoMotor getTipoMotor() {
        return tipoMotor;
    }

    @Override
    public void realizarServicio() {
        System.out.println("La lancha " + getPatente()
                + " realiza una excursion turistica acuatica. Motor: " + tipoMotor + ".");
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Tipo de motor: " + tipoMotor);
    }
}
