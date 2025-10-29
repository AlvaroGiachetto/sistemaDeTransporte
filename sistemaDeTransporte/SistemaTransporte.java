package sistemaDeTransporte;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Clase principal que maneja todo el sistema de transporte
public class SistemaTransporte {
    private ArrayList<Ruta> listaRutas;           // Guarda todas las rutas disponibles
    private ArrayList<Transporte> listaTransportes; // Guarda todos los transportes
    private ArrayList<Conductor> listaConductores;  // Lista de conductores
    private ArrayList<Viaje> listaViajes;           // Viajes creados
    private Scanner entrada;                        // Para leer datos del usuario
    private Random aleatorio;                       // Para generar datos aleatorios

    // Constructor: inicializa las listas y carga datos por defecto
    public SistemaTransporte() {
        listaRutas = new ArrayList<>();
        listaTransportes = new ArrayList<>();
        listaConductores = new ArrayList<>();
        listaViajes = new ArrayList<>();
        entrada = new Scanner(System.in);
        aleatorio = new Random();
        cargarDatosIniciales(); // Carga algunos datos base
    }

    // Agrega algunos datos para probar el sistema
    private void cargarDatosIniciales() {
        listaRutas.add(new Ruta("1", "Montevideo", "Colonia del Sacramento", 177, "terrestre"));
        listaRutas.add(new Ruta("2", "Montevideo", "Punta del Este", 135, "terrestre"));
        listaRutas.add(new Ruta("3", "Montevideo", "Buenos Aires (vuelo)", 230, "aereo"));
        listaRutas.add(new Ruta("4", "Montevideo", "Santiago, Chile (vuelo)", 1380, "aereo"));
        listaRutas.add(new Ruta("5", "Montevideo", "Buenos Aires (ferry)", 180, "maritimo"));

        listaTransportes.add(new Omnibus(1, "Omnibus De Larga Distancia", 40, 80, 4.5, "larga-distancia"));
        listaTransportes.add(new Omnibus(2, "Omnibus Interurbano", 30, 75, 4.0, "interurbano"));
        listaTransportes.add(new Avion(3, "Avion Regional", 100, 600, 12.0, "internacional"));
        listaTransportes.add(new Barco(4, "Barco tipo Ferry", 150, 40, 1.2, "ferry", "Montevideo"));

        listaConductores.add(new Conductor("1", "Juan", "terrestre"));
        listaConductores.add(new Conductor("2", "Maria", "terrestre"));
        listaConductores.add(new Conductor("3", "Lucas", "aereo"));
        listaConductores.add(new Conductor("4", "Carlos", "maritimo"));
    }

    // Inicia el menú principal del sistema
    public void iniciar() {
        while (true) {
            System.out.println("\n--- SISTEMA DE TRANSPORTE ---");
            System.out.println("1 - Ver rutas");
            System.out.println("2 - Crear viaje");
            System.out.println("3 - Ver viajes");
            System.out.println("4 - Reservar pasajeros");
            System.out.println("5 - Salir");
            System.out.print("Opción: ");

            String opcion = entrada.nextLine();
    // Opciones a elegir
            if (opcion.equals("1")) {
                mostrarRutas();
            } else if (opcion.equals("2")) {
                crearViaje();
            } else if (opcion.equals("3")) {
                mostrarViajes();
            } else if (opcion.equals("4")) {
                reservarPasajerosManual();
            } else if (opcion.equals("5")) {
                System.out.println("Saliendo...");
                break;
            } else {
                System.out.println("Opción inválida");
            }
        }
    }

    // Muestra las rutas disponibles
    private void mostrarRutas() {
        System.out.println("\nRUTAS DISPONIBLES:");
        for (Ruta ruta : listaRutas) {
            System.out.println(ruta.resumen());
        }
    }

    // Permite leer una fecha y validarla
    private LocalDate leerYValidarFecha() {
        System.out.print("Ingrese fecha (dd/MM/yyyy): ");
        String textoFecha = entrada.nextLine();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate fecha = LocalDate.parse(textoFecha, formato);
            LocalDate hoy = LocalDate.now();
            LocalDate limite = hoy.plusYears(1);

            if (fecha.isBefore(hoy)) {
                System.out.println("La fecha no puede ser anterior a hoy");
                return null;
            }

            if (fecha.isAfter(limite)) {
                System.out.println("La fecha no puede superar 1 año desde hoy");
                return null;
            }

            return fecha;
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido");
            return null;
        }
    }

    // Crear un nuevo viaje
    private void crearViaje() {
        mostrarRutas();
        System.out.print("Ingrese ID de ruta: ");
        String idSeleccionRuta = entrada.nextLine();

        Ruta rutaSeleccionada = null;

        for (Ruta ruta : listaRutas) {
            if (ruta.getId().equals(idSeleccionRuta)) {
                rutaSeleccionada = ruta;
                break;
            }
        }

        if (rutaSeleccionada == null) {
            System.out.println("Ruta no encontrada");
            return;
        }

        Transporte transporteSeleccionado = null;

        for (Transporte transporte : listaTransportes) {
            if (transporte.getTipo().equals(rutaSeleccionada.getTipo())) {
                transporteSeleccionado = transporte;
                break;
            }
        }

        if (transporteSeleccionado == null) {
            System.out.println("No hay transporte del tipo requerido");
            return;
        }

        Conductor conductorAsignado = null;

        for (Conductor conductor : listaConductores) {
            if (conductor.isDisponible() && conductor.getLicencia().equals(rutaSeleccionada.getTipo())) {
                conductorAsignado = conductor;
                break;
            }
        }

        if (conductorAsignado == null) {
            System.out.println("No hay conductores disponibles para ese tipo de ruta");
            return;
        }

        LocalDate fechaSeleccionada = leerYValidarFecha();
        if (fechaSeleccionada == null) {
            return;
        }

        int idNuevoViaje = listaViajes.size() + 1;
        Viaje viajeNuevo = new Viaje(idNuevoViaje, transporteSeleccionado, rutaSeleccionada, conductorAsignado, fechaSeleccionada);

        int capacidad = transporteSeleccionado.getCapacidad();
        int pasajerosAsignados = aleatorio.nextInt(capacidad) + 1;
        viajeNuevo.setPasajeros(pasajerosAsignados);

        conductorAsignado.setDisponible(false);
        listaViajes.add(viajeNuevo);

        System.out.println(viajeNuevo.resumen());
    }

    // Muestra todos los viajes creados
    private void mostrarViajes() {
        System.out.println("\nLISTA DE VIAJES:");
        if (listaViajes.isEmpty()) {
            System.out.println("No hay viajes disponibles.");
            return;
        }

        for (Viaje viaje : listaViajes) {
            System.out.println(viaje.resumen());
        }
    }

    // Permite modificar pasajeros manualmente
    private void reservarPasajerosManual() {
        mostrarViajes();
        System.out.print("Ingrese ID del viaje (0 para volver): ");
        String idViajeTexto = entrada.nextLine();

        if (idViajeTexto.equals("0")) {
            return;
        }

        int idViajeNum;

        try {
            idViajeNum = Integer.parseInt(idViajeTexto);
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar solo números.");
            return;
        }

        Viaje viajeEncontrado = null;

        for (Viaje viaje : listaViajes) {
            if (viaje.getId() == idViajeNum) {
                viajeEncontrado = viaje;
                break;
            }
        }

        if (viajeEncontrado == null) {
            System.out.println("Viaje no encontrado");
            return;
        }

        System.out.print("Cantidad de pasajeros (máx " + viajeEncontrado.getTransporte().getCapacidad() + "): ");
        String texto = entrada.nextLine();

        int cantidad;

        try {
            cantidad = Integer.parseInt(texto);
        } catch (Exception e) {
            System.out.println("Número inválido");
            return;
        }

        if (cantidad < 0 || cantidad > viajeEncontrado.getTransporte().getCapacidad()) {
            System.out.println("Fuera de rango");
            return;
        }

        viajeEncontrado.setPasajeros(cantidad);
        System.out.println("Pasajeros actualizados correctamente.");
        System.out.println(viajeEncontrado.resumen());
    }
}
