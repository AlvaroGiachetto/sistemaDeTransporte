package sistemaDeTransporte;

// Clase que representa a un conductor o piloto del sistema de transporte
public class Conductor {
    private String id;          // Identificador del conductor
    private String nombre;      // Nombre del conductor
    private String licencia;    // Tipo de licencia (terrestre, aereo o maritimo)
    private boolean disponible; // Indica si el conductor está disponible o no

    // Constructor del conductor
    public Conductor(String id, String nombre, String licencia) {
        this.id = id;
        this.nombre = nombre;
        this.licencia = licencia;
        this.disponible = true; // Por defecto, el conductor empieza disponible
    }

    // Métodos para obtener los datos
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    // Saber si está disponible o no
    public boolean isDisponible() {
        return disponible;
    }

    // Cambiar el estado de disponibilidad
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Devuelve un texto con la información básica del conductor
    public String resumen() {
        return id + " - " + nombre + " (" + licencia + ")";
    }
}
