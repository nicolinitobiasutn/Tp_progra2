public class PatenteDuplicadaException extends Exception {
    public PatenteDuplicadaException(String patente) {
        super("Ya existe un vehiculo registrado con la patente " + patente + ".");
    }
}
