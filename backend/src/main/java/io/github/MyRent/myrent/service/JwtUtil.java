package io.github.MyRent.myrent.service;

import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;


public interface JwtUtil {
    String extraerCorreo(String token);

    Date extraerExpiracion(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    Boolean isTokenExpired(String token);

    String generarToken(String username);

    String crearToken(Map<String, Object> claims, String subject);

    Boolean validarToken(String token, String username);
}