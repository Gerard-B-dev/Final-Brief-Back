// config/DataInitializer.java

package com.tienda.discos.config;

import com.tienda.discos.model.Product;
import com.tienda.discos.model.Task;
import com.tienda.discos.model.User;
import com.tienda.discos.repository.ProductRepository;
import com.tienda.discos.repository.TaskRepository;
import com.tienda.discos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Clase para inicializar datos en la base de datos.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si ya hay usuarios
        if (userRepository.count() == 0) {
            // Crear un usuario predeterminado
            User user = new User();
            user.setEmail("admin@tienda.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            userRepository.save(user);

            // Crear productos predeterminados
            Product p1 = new Product();
            p1.setTitle("Thriller - Michael Jackson");
            p1.setCoverImage("https://link_a_imagen1.jpg");
            p1.setPrice(15.99);
            p1.setDescription("El álbum más vendido de todos los tiempos.");
            p1.setUser(user);

            Product p2 = new Product();
            p2.setTitle("Back in Black - AC/DC");
            p2.setCoverImage("https://link_a_imagen2.jpg");
            p2.setPrice(12.99);
            p2.setDescription("Uno de los mejores álbumes de rock.");
            p2.setUser(user);

            Product p3 = new Product();
            p3.setTitle("The Dark Side of the Moon - Pink Floyd");
            p3.setCoverImage("https://link_a_imagen3.jpg");
            p3.setPrice(14.99);
            p3.setDescription("Clásico del rock progresivo.");
            p3.setUser(user);

            // ... Añade productos p4 a p15 de manera similar

            productRepository.saveAll(Arrays.asList(p1, p2, p3 /*, p4, ..., p15 */));

            // Crear tareas predeterminadas
            Task t1 = new Task();
            t1.setTitle("Comprar nuevos discos");
            t1.setCompleted(false);
            t1.setUser(user);

            Task t2 = new Task();
            t2.setTitle("Actualizar inventario");
            t2.setCompleted(true);
            t2.setUser(user);

            taskRepository.saveAll(Arrays.asList(t1, t2));
        }
    }
}


