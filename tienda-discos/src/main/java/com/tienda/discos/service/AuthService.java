package com.tienda.discos.service;
import com.tienda.discos.model.User;
import com.tienda.discos.repository.UserRepository;
import com.tienda.discos.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    /**
     * Registra un nuevo usuario y devuelve un token JWT.
     *
     * @param email    Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return Token JWT.
     */
    public String register(String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Usuario ya registrado");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return jwtUtil.generateToken(email);
    }
    /**
     * Autentica a un usuario y devuelve un token JWT.
     *
     * @param email    Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return Token JWT.
     */
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(email);
        }
        throw new RuntimeException("Credenciales inválidas");
    }
}