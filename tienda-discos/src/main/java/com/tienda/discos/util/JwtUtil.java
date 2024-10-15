package com.tienda.discos.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
/**
 * Clase de utilidad para manejar JWT.
 */
@Component
public class JwtUtil {
    private String secret = "tu_secreto_muy_seguro"; // Cambia esto a una clave más segura y almacénala de forma segura
    private long jwtExpirationInMs = 604800000L; // 7 días
    /**
     * Genera un token JWT basado en el correo electrónico.
     *
     * @param email Correo electrónico del usuario.
     * @return Token JWT.
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    /**
     * Obtiene el correo electrónico del usuario a partir del token JWT.
     *
     * @param token Token JWT.
     * @return Correo electrónico del usuario.
     */
    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    /**
     * Valida el token JWT.
     *
     * @param authToken Token JWT.
     * @return Verdadero si es válido, falso de lo contrario.
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            // Puedes manejar diferentes excepciones como ExpiredJwtException, MalformedJwtException, etc.
        }
        return false;
    }
}

