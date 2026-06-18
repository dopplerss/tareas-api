package org.example.tareasapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tareas")
@Tag(name = "Tareas", description = "API para gestionar tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ApiExternaClient apiExternaClient;

    @GetMapping("/posts-externos")
    public List<PostExterno> obtenerPostsExternos() {
        return apiExternaClient.obtenerPosts();
    }

    // Crear tarea
    @PostMapping
    @Operation(summary = "Crear una nueva tarea")
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Obtener todas
    @GetMapping
    @Operation(summary = "Obtener todas las tareas")
    public List<Tarea> obtenerTodas() {
        return tareaRepository.findAll();
    }

    // Obtener una por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una tarea por su ID")
    public Tarea obtenerPorId(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaRepository.findById(id);
        return tarea.orElse(null); // Por ahora, si no existe devuelve null
    }

    // Actualizar tarea (marcar como completada)
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tarea por ID")
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
    @Operation(summary = "Eliminar tarea por ID")
    public void eliminarTarea(@PathVariable Long id) {
        tareaRepository.deleteById(id);
    }

    // Obtener solo urgentes
    @GetMapping("/urgentes")
    @Operation(summary = "Obten tareas con mayor priodidad ")
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
    @Operation(summary = "Muestra cuantas tareas estan completas")
    public long contarCompletadas() {
        return tareaRepository.findAll().stream()
                .filter(Tarea::isCompletada)
                .count();
    }



    @GetMapping("/combinado")
    @Operation(summary = "devuelve un objeto con 2 campos")
    public Map<String, Object> obtenerCombinado() {
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("tareas", tareaRepository.findAll());
        resultado.put("posts", apiExternaClient.obtenerPosts().subList(0, 5)); // solo 5
        return resultado;
    }
}