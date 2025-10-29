package sistemaDeTransporte;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Clase que representa un viaje, que une un transporte, una ruta y un conductor
public class Viaje {
    // Atributos del viaje
    private int id;                 // Identificador del viaje
    private Transporte transporte;  // Transporte que se usa
    private Ruta ruta;              // Ruta seleccionada
    private Conductor conductor;    // Conductor asignado
    private LocalDate fecha;        // Fecha del viaje
    private int pasajeros;          // Cantidad de pasajeros asignados

    // Constructor para crear un nuevo viaje
    public Viaje(int id, Transporte transporte, Ruta ruta, Conductor conductor, LocalDate fecha) {
        this.id = id;
        this.transporte = transporte;
        this.ruta = ruta;
        this.conductor = conductor;
        this.fecha = fecha;
        this.pasajeros = 0; // Empieza sin pasajeros asignados
    }

    // Métodos para obtener los datos
    public int getId() { return id; }
    public Transporte getTransporte() { return transporte; }
    public Ruta getRuta() { return ruta; }
    public Conductor getConductor() { return conductor; }
    public LocalDate getFecha() { return fecha; }
    public int getPasajeros() { return pasajeros; }

    // Permite establecer la cantidad de pasajeros (dentro del límite del transporte)
    public void setPasajeros(int cantidad) {
        if (cantidad >= 0 && cantidad <= transporte.getCapacidad()) {
            this.pasajeros = cantidad;
        }
    }

    // Calcula la cantidad de paradas según el tipo de ruta
    private int calcularParadas() {
        String tipo = ruta.getTipo();

        if (tipo.equals("terrestre")) {
            return (int) (ruta.getDistanciaKm() / 150);
        } else if (tipo.equals("aereo")) {
            if (ruta.getDistanciaKm() < 800) {
                return 0;
            } else if (ruta.getDistanciaKm() < 2000) {
                return 1;
            } else {
                return 2;
            }
        } else if (tipo.equals("maritimo")) {
            return (int) (ruta.getDistanciaKm() / 400);
        } else {
            return 0;
        }
    }

    // Método sobrecargado (overload) que calcula el costo total base
    public double calcularCostoTotal() {
        double costoTransporte = transporte.calcularCosto(ruta.getDistanciaKm());
        double costoParadas = calcularParadas() * 20.0; // Ejemplo: 20 por cada parada
        double total = costoTransporte + costoParadas;
        return Math.round(total * 100.0) / 100.0;
    }

    // Sobrecarga del método anterior que puede incluir un impuesto del 10%
    public double calcularCostoTotal(boolean incluirImpuesto) {
        double base = calcularCostoTotal();
        if (incluirImpuesto) {
            return Math.round(base * 1.10 * 100.0) / 100.0;
        } else {
            return base;
        }
    }

    // Calcula el precio por pasajero (según cantidad o capacidad)
    public double calcularPrecioPorPasajero(boolean incluirImpuesto) {
        int divisor = pasajeros > 0 ? pasajeros : transporte.getCapacidad();
        double total = calcularCostoTotal(incluirImpuesto);
        double precio = total / divisor;
        return Math.round(precio * 100.0) / 100.0;
    }

    // Método que muestra toda la información del viaje ordenada hacia abajo
    public String resumen() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        double horas = transporte.calcularDuracionHoras(ruta.getDistanciaKm());
        int horasEnteras = (int) horas;
        int minutos = (int) ((horas - horasEnteras) * 60);
        double estimado = calcularCostoTotal();
        double precioPorPasajero = calcularPrecioPorPasajero(false);

        // Todos los Datos del viaje
        return "\n--- VIAJE ---"
             + "\nID: " + id
             + "\nFecha: " + fecha.format(formato)
             + "\nOrigen: " + ruta.getOrigen()
             + "\nDestino: " + ruta.getDestino()
             + "\nTransporte: " + transporte.getModelo()
             + "\nConductor: " + conductor.getNombre()
             + "\nDuración estimada: " + horasEnteras + "h " + minutos + "m"
             + "\nPasajeros: " + pasajeros
             + "\nCosto operativo: $" + estimado
             + "\nPrecio por pasajero: $" + precioPorPasajero
             + "\n---------------------------";
    }
}
