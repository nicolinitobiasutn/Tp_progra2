import java.time.Year;
import java.util.Scanner;

public class Main {
    private static final int SALIR = 8;

    public static void main(String[] args) {
        GestorVehiculos gestor = new GestorVehiculos();
        Scanner scanner = new Scanner(System.in);
        cargarDatosDePrueba(gestor);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Ingrese una opcion: ");

            switch (opcion) {
                case 1:
                    agregarVehiculo(gestor, scanner);
                    break;
                case 2:
                    mostrarLista("No hay vehiculos registrados.", gestor.listar());
                    break;
                case 3:
                    if (gestor.estaVacio()) {
                        System.out.println("No hay vehiculos para realizar servicios.");
                    } else {
                        gestor.realizarServicios();
                    }
                    break;
                case 4:
                    System.out.print("Ingrese la patente a buscar: ");
                    String patente = scanner.nextLine();
                    gestor.buscarPorPatente(patente).ifPresentOrElse(
                            v -> v.mostrarDatos(),
                            () -> System.out.println("No se encontro ningun vehiculo con la patente " + patente.trim().toUpperCase() + ".")
                    );
                    break;
                case 5:
                    int capMinima = leerEntero(scanner, "Ingrese la capacidad minima de pasajeros: ");
                    mostrarLista("No hay vehiculos que superen esa capacidad.",
                            gestor.filtrarPorCapacidadMayorA(capMinima));
                    break;
                case 6:
                    System.out.println("Vehiculos ordenados por anio (descendente):");
                    mostrarLista("No hay vehículos registrados.", gestor.ordenadosPorAnioDescendente());
                    break;
                case 7:
                    System.out.println("Vehiculos ordenados por capacidad (descendente):");
                    mostrarLista("No hay vehiculos registrados.", gestor.ordenadosPorCapacidadDescendente());
                    break;
                case SALIR:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción invalida. Ingrese un número del 1 al 8.");
            }
        } while (opcion != SALIR);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- SISTEMA DE GESTION DE TRANSPORTE TURISTICO ---");
        System.out.println("1. Agregar vehiculo");
        System.out.println("2. Mostrar todos los vehiculos");
        System.out.println("3. Realizar servicios turisticos");
        System.out.println("4. Buscar vehiculo por patente");
        System.out.println("5. Mostrar vehiculos con capacidad mayor a un valor");
        System.out.println("6. Mostrar vehiculos ordenados por anio de fabricacion (desc.)");
        System.out.println("7. Mostrar vehiculos ordenados por capacidad (desc.)");
        System.out.println("8. Salir");
    }

    private static void mostrarLista(String mensajeVacia, java.util.List<Vehiculo> lista) {
        if (lista.isEmpty()) {
            System.out.println(mensajeVacia);
            return;
        }
        for (Vehiculo v : lista) {
            System.out.println(v);
        }
    }

    private static void agregarVehiculo(GestorVehiculos gestor, Scanner scanner) {
        System.out.println("\n--- TIPO DE VEHICULO ---");
        System.out.println("1. Colectivo turistico");
        System.out.println("2. Van ejecutiva");
        System.out.println("3. Lancha turistica");
        int tipo = leerEntero(scanner, "Seleccione el tipo (1-3): ");

        try {
            Vehiculo nuevo = crearVehiculo(tipo, scanner);
            gestor.agregar(nuevo);
            System.out.println("Vehiculo agregado correctamente!");
        } catch (DatoInvalidoException | PatenteDuplicadaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Vehiculo crearVehiculo(int tipo, Scanner scanner)
            throws DatoInvalidoException, PatenteDuplicadaException {
        if (tipo < 1 || tipo > 3) {
            throw new DatoInvalidoException("Tipo de vehiculo invalido.");
        }

        System.out.print("Ingrese patente: ");
        String patente = scanner.nextLine();
        System.out.print("Ingrese marca: ");
        String marca = scanner.nextLine();
        int capacidad = leerEntero(scanner, "Ingrese capacidad de pasajeros: ");
        int anio = leerEntero(scanner, "Ingrese anio de fabricacion: ");

        switch (tipo) {
            case 1:
                int pisos = leerEntero(scanner, "Ingrese cantidad de pisos (1 o 2): ");
                return new TouristBus(patente, marca, capacidad, anio, pisos);
            case 2:
                System.out.print("Tiene aire acondicionado? (s/n): ");
                String resp = scanner.nextLine().trim().toLowerCase();
                boolean aire = resp.equals("s") || resp.equals("si") || resp.equals("SI");
                return new ExecutiveVan(patente, marca, capacidad, anio, aire);
            default:
                System.out.print("Ingrese tipo de motor (" + motoresValidos() + "): ");
                String motorTexto = scanner.nextLine();
                try {
                    TipoMotor motor = TipoMotor.valueOf(motorTexto.trim().toUpperCase());
                    return new TouristBoat(patente, marca, capacidad, anio, motor);
                } catch (IllegalArgumentException e) {
                    throw new DatoInvalidoException("Tipo de motor invalido. Opciones: " + motoresValidos() + ".");
                }
        }
    }

    private static String motoresValidos() {
        StringBuilder sb = new StringBuilder();
        for (TipoMotor m : TipoMotor.values()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(m.name());
        }
        return sb.toString();
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[Error] Debe ingresar un numero entero valido.");
            }
        }
    }

    private static void cargarDatosDePrueba(GestorVehiculos gestor) {
        try {
            gestor.agregar(new TouristBus("AA123BB", "Mercedes Benz", 45, 2020, 2));
            gestor.agregar(new TouristBus("AB456CD", "Scania", 50, 2022, 1));
            gestor.agregar(new ExecutiveVan("AC789EF", "Renault", 15, 2021, true));
            gestor.agregar(new ExecutiveVan("AD321GH", "Toyota", 12, 2019, false));
            gestor.agregar(new TouristBoat("LAN001", "Yamaha", 8, 2023, TipoMotor.NAFTA));
            gestor.agregar(new TouristBoat("LAN002", "Mercury", 10, 2022, TipoMotor.ELECTRICO));
        } catch (DatoInvalidoException | PatenteDuplicadaException e) {
            System.out.println("Error en datos de prueba: " + e.getMessage());
        }
    }
}
