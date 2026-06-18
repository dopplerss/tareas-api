package org.example.tareasapi;
 import jakarta.persistence.*;
 import jakarta.validation.constraints.Min;
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.NotNull;
 import jakarta.validation.constraints.Size;


@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El estado 'completada' es obligatorio")
    private boolean completada;

    @Min(value = 1, message = "La prioridad mínima es 1")
    @NotNull(message = "La prioridad es obligatoria")
    private int prioridad;

    // Constructor vacío (necesario para JSON)
    public Tarea() {}

    // Constructor con parámetros
    public Tarea(String nombre, boolean completada, int prioridad){

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
