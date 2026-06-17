package org.example.tareasapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Spring Data JPA genera automáticamente los métodos CRUD
    // findAll(), findById(), save(), deleteById(), etc.

    List<Tarea> findByPrioridadGreaterThanEqual(int prioridad);
}


