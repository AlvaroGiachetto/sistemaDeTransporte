package sistemaDeTransporte;

// Clase que representa un avión (tipo de transporte aéreo)
public class Avion extends Transporte {
    private String tipoVuelo; // Puede ser "regional" o "internacional"

    // Constructor del avión
    public Avion(int id, String modelo, int capacidad, double velocidadKmH, double costoPorKm, String tipoVuelo) {
        // Llama al constructor de Transporte con tipo "aereo"
        super(id, modelo, "aereo", capacidad, velocidadKmH, costoPorKm);
        this.tipoVuelo = tipoVuelo;
    }

    public String getTipoVuelo() {
        return tipoVuelo;
    }

    // Implementación del cálculo de costo para los aviones
    @Override
    public double calcularCosto(double distanciaKm) {
        double costoBase = distanciaKm * getCostoPorKm();

        // Los vuelos regionales tienen un aumento del 30%
        // Los internacionales un aumento del 60%
        double factor = tipoVuelo.equals("regional") ? 1.3 : 1.6;

        // Se redondea el resultado
        double resultado = Math.round(costoBase * factor * 100.0) / 100.0;
        return resultado;
    }

    // Calcula la duración según la velocidad del avión
    @Override
    public double calcularDuracionHoras(double distanciaKm) {
        return distanciaKm / getVelocidadKmH();
    }
}
