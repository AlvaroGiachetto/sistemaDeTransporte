package sistemaDeTransporte;

// Clase que representa un ómnibus (tipo de transporte terrestre)
public class Omnibus extends Transporte {
    private String tipoServicio; // Puede ser "larga-distancia" o "interurbano"

    // Constructor: recibe todos los datos necesarios para crear un ómnibus
    public Omnibus(int id, String modelo, int capacidad, double velocidadKmH, double costoPorKm, String tipoServicio) {
        // Llamamos al constructor de la clase padre (Transporte)
        super(id, modelo, "terrestre", capacidad, velocidadKmH, costoPorKm);
        this.tipoServicio = tipoServicio;
    }

    // Devuelve el tipo de servicio del ómnibus
    public String getTipoServicio() {
        return tipoServicio;
    }

    // Implementación del método abstracto calcularCosto (cada clase lo hace distinto)
    @Override
    public double calcularCosto(double distanciaKm) {
        // Calcula el costo base multiplicando la distancia por el costo por km
        double costoBase = distanciaKm * getCostoPorKm();

        // Si es de larga distancia, se le aplica un pequeño aumento (5%)
        // Si es interurbano, se le baja un poco el costo (5%)
        double factor = tipoServicio.equals("larga-distancia") ? 1.05 : 0.95;

        // Se redondea el resultado a 2 decimales
        double resultado = Math.round(costoBase * factor * 100.0) / 100.0;
        return resultado;
    }

    // Calcula la duración del viaje dividiendo la distancia entre la velocidad
    @Override
    public double calcularDuracionHoras(double distanciaKm) {
        return distanciaKm / getVelocidadKmH();
    }
}
