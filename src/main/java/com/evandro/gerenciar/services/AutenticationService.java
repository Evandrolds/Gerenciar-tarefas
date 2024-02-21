package com.evandro.gerenciar.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Date;

public class AutenticationService {
    public static void addTokenJwt(HttpServletResponse response, String username) {
        String tokenJwt = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.ES512, "signinkey")
                .compact();

        response.addHeader("Authorization", "Bearer " + tokenJwt);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            Claims user = Jwts.parser()
                    .setSigningKey("signinkey")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, null);
            } else {
                throw new RuntimeException("Falha na autenticação!");
            }
        }
        return null;
    }
}
