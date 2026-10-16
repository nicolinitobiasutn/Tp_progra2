public abstract class Vehiculo {
    protected String patente;
    protected String marca;
    protected int capacidadPasajeros;
    protected int anioFabricacion;

    public Vehiculo(String patente, String marca, int capacidadPasajeros, int anioFabricacion) {
        this.patente = patente;
        this.marca = marca;
        this.capacidadPasajeros = capacidadPasajeros;
        this.anioFabricacion = anioFabricacion;
    }
    
    public String toString() {
    return "Patente: " + patente
         + " | Marca: " + marca
         + " | Capacidad: " + capacidadPasajeros
         + " | Anio: " + anioFabricacion;
}
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
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

    public abstract void realizarServicio();

    public void mostrarDatos() {
        System.out.println("Tipo: " + getClass().getSimpleName());
        System.out.println("Patente: " + patente);
        System.out.println("Marca: " + marca);
        System.out.println("Capacidad: " + capacidadPasajeros + " pasajeros");
        System.out.println("Anio de fabricacion: " + anioFabricacion);
    }

}
