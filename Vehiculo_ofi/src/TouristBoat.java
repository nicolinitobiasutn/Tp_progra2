public class TouristBoat extends Vehiculo {
    private String tipoMotor; 

    public TouristBoat(String patente, String marca, int capacidadPasajeros, int anioFabricacion, String tipoMotor) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.tipoMotor = tipoMotor;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    
    public void realizarServicio() {
        System.out.println("La lancha " + getPatente()
                + " realiza una excursion turistica acuatica. Motor: " + tipoMotor + ".");
    }

    
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Tipo de motor: " + tipoMotor);
    }
}