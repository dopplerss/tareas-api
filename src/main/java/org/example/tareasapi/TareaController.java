package org.example.tareasapi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TareaController {

    // Datos de ejemplo (por ahora en memoria)
    private List<Tarea> tareas = new ArrayList<>();

    public TareaController() {
        // Agregamos tareas de ejemplo al iniciar
        tareas.add(new Tarea(1L, "Aprender Spring Boot", false, 9));
        tareas.add(new Tarea(2L, "Hacer ejercicio", false, 5));
        tareas.add(new Tarea(3L, "Leer un libro", false, 3));
    }

    @GetMapping("/tareas")
    public List<Tarea> obtenerTareas() {
        return tareas;
    }

    @GetMapping("/tareas/urgentes")
    public List<Tarea> obtenerTareasUrgentes() {
        List<Tarea> urgentes = new ArrayList<>();
        for (Tarea t : tareas) {
            if (t.getPrioridad() >= 8) {
                urgentes.add(t);
            }
        }
        return urgentes;
    }

    @GetMapping("/tareas/{id}")
    public Tarea obtenerTareaPorId(@PathVariable Long id) {
        for (Tarea t : tareas) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
}
