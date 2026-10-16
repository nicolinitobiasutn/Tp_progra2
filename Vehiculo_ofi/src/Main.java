import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Precarga de datos de prueba opcionales según consigna[cite: 3] 
        listaVehiculos.add(new TouristBus("AA123BB", "Mercedes Benz", 45, 2020, 2));
        listaVehiculos.add(new TouristBus("AB456CD", "Scania", 50, 2022, 1));
        listaVehiculos.add(new ExecutiveVan ("AC789EF", "Renault", 15, 2021, true));
        listaVehiculos.add(new ExecutiveVan ("AD321GH", "Toyota", 12, 2019, false));
        listaVehiculos.add(new TouristBoat("LAN001", "Yamaha", 8, 2023, "NAFTA"));
        listaVehiculos.add(new TouristBoat("LAN002", "Mercury", 10, 2022, "ELECTRICO"));

        do {
            System.out.println("\n--- SISTEMA DE GESTION DE TRANSPORTE TURISTICO ---");
            System.out.println("1. Agregar vehiculo");
            System.out.println("2. Mostrar todos los vehiculos");
            System.out.println("3. Realizar servicios turisticos");
            System.out.println("4. Buscar vehiculo por patente");
            System.out.println("5. Mostrar vehiculos con capacidad mayor a un valor ingresado");
            System.out.println("6. Mostrar vehiculos ordenados por anio de fabricacion descendente");
            System.out.println("7. Mostrar vehiculos ordenados por capacidad de pasajeros descendente");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarVehiculo(listaVehiculos, scanner);
                        break;
                    case 2:
                        if (listaVehiculos.isEmpty()) {
                            System.out.println("No hay vehiculos registrados.");
                        } else {
                            for (Vehiculo v : listaVehiculos) {
                                System.out.println(v);
                            }
                        }
                        break;
                    case 3:
                        if (listaVehiculos.isEmpty()) {
                            System.out.println("No hay vehiculos para realizar servicios.");
                        } else {

                            for (Vehiculo v : listaVehiculos) {
                                v.realizarServicio();
                            }
                        }
                        break;
                    case 4:
                        System.out.print("Ingrese la patente a buscar: ");
                        String patenteBuscada = scanner.nextLine().trim().toUpperCase();
                        Vehiculo encontrado = buscarPorPatente(listaVehiculos, patenteBuscada);
                        if (encontrado != null) {
                            System.out.println("Vehiculo encontrado: " + encontrado);
                        } else {
                            System.out.println("No se encontro ningun vehículo con la patente " + patenteBuscada);
                        }
                        break;
                    case 5:
                        int capMinima = leerEntero(scanner, "Ingrese la capacidad minima de pasajeros: ");
                        boolean hayCoincidencias = false;
                        for (Vehiculo v : listaVehiculos) {
                            if (v.getCapacidadPasajeros() > capMinima) {
                                System.out.println(v);
                                hayCoincidencias = true;
                            }
                        }
                        if (!hayCoincidencias) {
                            System.out.println("No hay vehiculos que superen esa capacidad.");
                        }
                        break;
                    case 6:
                        ordenarPorAnioDescendente(listaVehiculos);
                        System.out.println("Vehiculos ordenados por anio (descendente):");
                        for (Vehiculo v : listaVehiculos) {
                            System.out.println(v);
                        }
                        break;
                    case 7:
                        ordenarPorCapacidadDescendente(listaVehiculos);
                        System.out.println("Vehiculos ordenados por capacidad (descendente):");
                        for (Vehiculo v : listaVehiculos) {
                            System.out.println(v);
                        }
                        break;
                    case 8:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opcion invalida. Ingrese un numero del 1 al 8.");
                }
            } catch (NumberFormatException e) {

                System.out.println("[Error] Entrada no numerica valida. Por favor, intente nuevamente.");
            }
        } while (opcion != 8);
        
        scanner.close();
    }


    public static void agregarVehiculo(ArrayList<Vehiculo> lista, Scanner scanner) {
        System.out.println("\n--- TIPO DE VEHÍCULO ---");
        System.out.println("1. Colectivo Turístico");
        System.out.println("2. Van Ejecutiva");
        System.out.println("3. Lancha Turística");
        int tipo = leerEntero(scanner, "Seleccione el tipo (1-3): ");

        if (tipo < 1 || tipo > 3) {
            System.out.println("Error: Tipo de vehículo inválido.");
            return;
        }


        String patente = "";
        while (true) {
            System.out.print("Ingrese patente: ");
            patente = scanner.nextLine().trim().toUpperCase();
            if (patente.isEmpty()) {
                System.out.println("Error: La patente no puede estar vacía.");
                continue;
            }
            if (buscarPorPatente(lista, patente) != null) {
                System.out.println("Error: Ya existe un vehículo registrado con la patente " + patente + " (Vehículo duplicado).");
                return;
            }
            break;
        }

        System.out.print("Ingrese marca: ");
        String marca = scanner.nextLine().trim();


        int capacidad = 0;
        while (true) {
            capacidad = leerEntero(scanner, "Ingrese capacidad de pasajeros: ");
            if (capacidad <= 0) {
                System.out.println("Error: La capacidad debe ser mayor a cero.");
                continue;
            }
            break;
        }


        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        int anio = 0;
        while (true) {
            anio = leerEntero(scanner, "Ingrese año de fabricación: ");
            if (anio < 1990 || anio > anioActual) {
                System.out.println("Error: El año debe ser mayor o igual a 1990 y no mayor al actual (" + anioActual + ").");
                continue;
            }
            break;
        }


        if (tipo == 1) {
            int pisos = 0;
            while (true) {
                pisos = leerEntero(scanner, "Ingrese cantidad de pisos (1 o 2): ");
                if (pisos == 1 || pisos == 2) break;
                System.out.println("Error: La cantidad de pisos debe ser 1 o 2.");
            }
            lista.add(new TouristBus(patente, marca, capacidad, anio, pisos));
            System.out.println("¡Colectivo agregado correctamente!");

        } else if (tipo == 2) {
            System.out.print("¿Tiene aire acondicionado? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            boolean aire = respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("true");
            lista.add(new ExecutiveVan(patente, marca, capacidad, anio, aire));
            System.out.println("¡Van ejecutiva agregada correctamente!");

        } else if (tipo == 3) {
            String motor = "";
            while (true) {
                System.out.print("Ingrese tipo de motor (NAFTA / DIESEL / ELECTRICO): ");
                motor = scanner.nextLine().trim().toUpperCase();
                if (motor.equals("NAFTA") || motor.equals("DIESEL") || motor.equals("ELECTRICO")) {
                    break;
                }
                System.out.println("Error: Tipo de motor invalido. Opciones válidas: NAFTA, DIESEL, ELECTRICO.");
            }
            lista.add(new TouristBoat(patente, marca, capacidad, anio, motor));
            System.out.println("¡Lancha turistica agregada correctamente!");
        }
    }


    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[Error] Debe ingresar un número entero válido.");
            }
        }
    }


    public static Vehiculo buscarPorPatente(ArrayList<Vehiculo> lista, String patente) {
        for (Vehiculo v : lista) {
            if (v.getPatente().equalsIgnoreCase(patente)) {
                return v;
            }
        }
        return null;
    }


    public static void ordenarPorAnioDescendente(ArrayList<Vehiculo> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getAnioFabricacion() < lista.get(j + 1).getAnioFabricacion()) {
                    Vehiculo temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }


    public static void ordenarPorCapacidadDescendente(ArrayList<Vehiculo> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getCapacidadPasajeros() < lista.get(j + 1).getCapacidadPasajeros()) {
                    Vehiculo temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }
}