package sistemaDeTransporte;

// Clase abstracta que representa un medio de transporte general (avión, barco, ómnibus, etc.)
public abstract class Transporte {
    // Atributos comunes para todos los transportes
    private int id;                // Identificador del transporte
    private String modelo;         // Modelo o nombre del transporte
    private String tipo;           // Tipo (terrestre, aéreo o marítimo)
    private int capacidad;         // Cantidad máxima de pasajeros
    private double velocidadKmH;   // Velocidad promedio en kilómetros por hora
    private double costoPorKm;     // Costo base por cada kilómetro recorrido

    // Constructor para inicializar los datos del transporte
    public Transporte(int id, String modelo, String tipo, int capacidad, double velocidadKmH, double costoPorKm) {
        // Se asignan los valores a cada atributo
        this.id = id;
        this.modelo = modelo;
        this.tipo = tipo;

        // Se usa Math.max para evitar valores negativos o cero
        this.capacidad = Math.max(1, capacidad);
        this.velocidadKmH = Math.max(1.0, velocidadKmH);
        this.costoPorKm = Math.max(0.0, costoPorKm);
    }

    // Métodos "get" para obtener los datos del transporte
    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getVelocidadKmH() {
        return velocidadKmH;
    }

    public double getCostoPorKm() {
        return costoPorKm;
    }

    // Métodos abstractos que cada tipo de transporte va a implementar a su manera
    // Estos métodos serán diferentes según si es avión, barco o bus
    public abstract double calcularCosto(double distanciaKm);
    public abstract double calcularDuracionHoras(double distanciaKm);

    // Método que devuelve un texto con la información básica del transporte
    public String resumen() {
        return id + " - " + modelo + " (" + tipo + ", capacidad: " + capacidad + ")";
    }
}
