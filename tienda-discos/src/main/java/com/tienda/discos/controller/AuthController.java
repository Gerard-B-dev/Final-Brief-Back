// controller/AuthController.java

package com.tienda.discos.controller;

import com.tienda.discos.model.User;
import com.tienda.discos.repository.UserRepository;
import com.tienda.discos.service.AuthService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para manejar la autenticación de usuarios.
 */
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param authRequest Solicitud de autenticación.
     * @return Token JWT y datos del usuario.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
        try {
            String token = authService.register(authRequest.getEmail(), authRequest.getPassword());
            User user = userRepository.findByEmail(authRequest.getEmail());
            UserDto userDto = new UserDto(user);
            return ResponseEntity.ok(new AuthResponse(token, userDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para autenticar a un usuario existente.
     *
     * @param authRequest Solicitud de autenticación.
     * @return Token JWT y datos del usuario.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            String token = authService.login(authRequest.getEmail(), authRequest.getPassword());
            User user = userRepository.findByEmail(authRequest.getEmail());
            UserDto userDto = new UserDto(user);
            return ResponseEntity.ok(new AuthResponse(token, userDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Clase interna para manejar las solicitudes de autenticación.
     */
    @Getter
    @Setter
    public static class AuthRequest {
        private String email;
        private String password;
    }

    /**
     * Clase interna para manejar las respuestas de autenticación.
     */
    @Getter
    @Setter
    public static class AuthResponse {
        private String token;
        private UserDto user;

        public AuthResponse(String token, UserDto user) {
            this.token = token;
            this.user = user;
        }
    }

    /**
     * Clase DTO para el usuario.
     */
    @Getter
    @Setter
    public static class UserDto {
        private Long id;
        private String email;

        public UserDto(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
        }
    }
}

