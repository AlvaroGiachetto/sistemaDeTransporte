package sistemaDeTransporte;

// Clase que representa un barco o ferry (tipo marítimo)
public class Barco extends Transporte {
    private String tipoBarco;   // Tipo de barco (por ejemplo "ferry")
    private String puertoBase;  // Puerto donde opera normalmente

    // Constructor del barco
    public Barco(int id, String modelo, int capacidad, double velocidadKmH, double costoPorKm, String tipoBarco, String puertoBase) {
        super(id, modelo, "maritimo", capacidad, velocidadKmH, costoPorKm);
        this.tipoBarco = tipoBarco;
        this.puertoBase = puertoBase;
    }

    public String getTipoBarco() {
        return tipoBarco;
    }

    public String getPuertoBase() {
        return puertoBase;
    }

    // Calcula el costo total del viaje marítimo
    @Override
    public double calcularCosto(double distanciaKm) {
        double base = distanciaKm * getCostoPorKm();
        double tarifaPuerto = 50.0; // Tarifa fija por uso de puerto de 50$
        double resultado = Math.round((base + tarifaPuerto) * 100.0) / 100.0;
        return resultado;
    }

    // Calcula la duración en horas (distancia / velocidad)
    @Override
    public double calcularDuracionHoras(double distanciaKm) {
        double horas = distanciaKm / getVelocidadKmH();
        return horas;
    }
}
