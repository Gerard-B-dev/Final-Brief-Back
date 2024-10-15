// service/TaskService.java
package com.tienda.discos.service;
import com.tienda.discos.model.Task;
import com.tienda.discos.model.User;
import com.tienda.discos.repository.TaskRepository;
import com.tienda.discos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    /**
     * Obtiene todas las tareas de un usuario específico.
     *
     * @param userId ID del usuario.
     * @return Lista de tareas.
     */
    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }
    /**
     * Crea una nueva tarea para un usuario específico.
     *
     * @param task   Tarea a crear.
     * @param userId ID del usuario.
     * @return Tarea creada.
     */
    public Task createTask(Task task, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        task.setUser(user);
        return taskRepository.save(task);
    }
    /**
     * Actualiza una tarea existente.
     *
     * @param id   ID de la tarea.
     * @param task Datos actualizados de la tarea.
     * @return Tarea actualizada.
     */
    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        existingTask.setTitle(task.getTitle());
        existingTask.setCompleted(task.isCompleted());
        return taskRepository.save(existingTask);
    }
    /**
     * Elimina una tarea por su ID.
     *
     * @param id ID de la tarea.
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    /**
     * Obtiene el ID del usuario basado en el correo electrónico.
     *
     * @param email Correo electrónico del usuario.
     * @return ID del usuario.
     */
    public Long getUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getId();
        }
        throw new RuntimeException("Usuario no encontrado");
    }
}