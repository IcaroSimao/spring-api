/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.cadastro.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ISMsi
 */
@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    public String generateToken(String usuario){
        return Jwts.builder()
                .setSubject(usuario)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
    
    public boolean tokenValido(String token){
        // claims significa reivindicações
        Claims claims = getClaims(token);
        if (claims != null){
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (username != null && expirationDate != null && now.before(expirationDate)){
                return true;
            }
        }
        return false;
    }

    public String getUsername(String token){
        Claims claims = getClaims(token);
        if (claims != null){
            return claims.getSubject();
        }
        return null;
    }
    
    // Obter claims atraves de um token
    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
