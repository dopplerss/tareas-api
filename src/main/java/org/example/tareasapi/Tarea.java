package org.example.tareasapi;


public class Tarea {
    private Long id;
    private String nombre;
    private boolean completada;
    private int prioridad;

    // Constructor vacío (necesario para JSON)
    public Tarea() {}

    // Constructor con parámetros
    public Tarea(Long id, String nombre, boolean completada, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.completada = completada;
        this.prioridad = prioridad;
    }

    // Getters y Setters (Lombok los genera, pero por ahora los escribimos)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }

    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }
}
