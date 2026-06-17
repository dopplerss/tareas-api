package org.example.tareasapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    // Crear tarea
    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Obtener todas
    @GetMapping
    public List<Tarea> obtenerTodas() {
        return tareaRepository.findAll();
    }

    // Obtener una por ID
    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaRepository.findById(id);
        return tarea.orElse(null); // Por ahora, si no existe devuelve null
    }

    // Actualizar tarea (marcar como completada)
    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada) {
        Optional<Tarea> tareaOptional = tareaRepository.findById(id);
        if (tareaOptional.isPresent()) {
            Tarea tarea = tareaOptional.get();
            tarea.setNombre(tareaActualizada.getNombre());
            tarea.setCompletada(tareaActualizada.isCompletada());
            tarea.setPrioridad(tareaActualizada.getPrioridad());
            return tareaRepository.save(tarea);
        }
        return null;
    }

    // Eliminar tarea
    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareaRepository.deleteById(id);
    }

    // Obtener solo urgentes
    @GetMapping("/urgentes")
    public List<Tarea> obtenerUrgentes() {
        List<Tarea> todas = tareaRepository.findAll();
        return todas.stream()
                .filter(t -> t.getPrioridad() >= 8)
                .toList();
    }


    @GetMapping("/mayor-que/{prioridad}")
    public List<Tarea> obtenerMayorQue(@PathVariable int prioridad) {
        return tareaRepository.findByPrioridadGreaterThanEqual(prioridad);
    }

    @GetMapping("/contar-completadas")
    public long contarCompletadas() {
        return tareaRepository.findAll().stream()
                .filter(Tarea::isCompletada)
                .count();
    }
}