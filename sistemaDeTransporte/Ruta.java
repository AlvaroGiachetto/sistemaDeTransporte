package sistemaDeTransporte;

// Clase que representa una ruta o recorrido entre dos lugares
public class Ruta {
    // Atributos de la ruta
    private String id;          // Identificador de la ruta
    private String origen;      // Ciudad de origen
    private String destino;     // Ciudad de destino
    private double distanciaKm; // Distancia total del recorrido en kilómetros
    private String tipo;        // Tipo de transporte que usa (terrestre, aéreo o marítimo)

    // Constructor para crear una ruta nueva
    public Ruta(String id, String origen, String destino, double distanciaKm, String tipo) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        // Math.max evita valores negativos o cero
        this.distanciaKm = Math.max(1.0, distanciaKm);
        this.tipo = tipo;
    }

    // Métodos para obtener los datos de la ruta
    public String getId() {
        return id;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public String getTipo() {
        return tipo;
    }

    // Devuelve un texto con los datos de la ruta
    public String resumen() {
        return id + " - " + origen + " --> " + destino + " (" + (int) distanciaKm + " km, " + tipo + ")";
    }
}
