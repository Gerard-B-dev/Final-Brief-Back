
// controller/TaskController.java

package com.tienda.discos.controller;

import com.tienda.discos.model.Task;
import com.tienda.discos.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones CRUD de tareas.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * Obtiene todas las tareas del usuario autenticado.
     *
     * @param authentication Información de autenticación.
     * @return Lista de tareas.
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(Authentication authentication) {
        String email = authentication.getName();
        Long userId = taskService.getUserIdByEmail(email);
        List<Task> tasks = taskService.getAllTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Crea una nueva tarea para el usuario autenticado.
     *
     * @param task           Tarea a crear.
     * @param authentication Información de autenticación.
     * @return Tarea creada.
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, Authentication authentication) {
        String email = authentication.getName();
        Long userId = taskService.getUserIdByEmail(email);
        Task createdTask = taskService.createTask(task, userId);
        return ResponseEntity.ok(createdTask);
    }

    /**
     * Actualiza una tarea existente.
     *
     * @param id   ID de la tarea.
     * @param task Datos actualizados de la tarea.
     * @return Tarea actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Elimina una tarea existente.
     *
     * @param id ID de la tarea.
     * @return Estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}

