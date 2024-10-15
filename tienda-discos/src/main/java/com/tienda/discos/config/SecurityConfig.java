
package com.tienda.discos.config;

import com.tienda.discos.filter.JwtAuthenticationFilter;
import com.tienda.discos.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad utilizando JWT.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Configura el filtro de seguridad para interceptar las solicitudes.
     *
     * @param http Objeto HttpSecurity.
     * @return Configuración de la cadena de filtros.
     * @throws Exception Si ocurre un error de configuración.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Crear una instancia del filtro JWT
        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtUtil);

        http
                // Deshabilitar CSRF ya que no se usa en APIs REST
                .csrf(csrf -> csrf.disable())
                // Configurar la autorización de las solicitudes
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/register", "/api/login").permitAll()
                        .anyRequest().authenticated()
                )
                // Configurar la gestión de sesiones como sin estado
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Añadir el filtro JWT antes del filtro de autenticación de Spring
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Configura el AuthenticationManager para manejar la autenticación.
     *
     * @param authConfig Configuración de autenticación.
     * @return AuthenticationManager.
     * @throws Exception Si ocurre un error.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

