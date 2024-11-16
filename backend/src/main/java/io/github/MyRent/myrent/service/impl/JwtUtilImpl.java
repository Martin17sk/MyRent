package io.github.MyRent.myrent.service.impl;

import io.github.MyRent.myrent.service.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.security.Key;
@Service
public class JwtUtilImpl implements JwtUtil {
    private static final String SECRET_KEY = "a4bfab087ea1f5808a3929884c0b31e1aff7da38234230c87d376a46c3329f4999fd24a26b89ba9cf1664cdcf7bf415c5e577089553588ec0908c493f7839f880465eb02f7a8027752a70a26bb94fa05e760257da968dc024e3edabd688df9f27c039b44015ca385ea41ecea33be5e8b9cfcf5a1fdc2f76507590c9964c7520b52f75c7bb7a52272127658c099afe272f0f0696bd6d61d8033a8321a007078a36d2fb03548618e1e7542d454d818d88559665db8189bb125845303f101956e9c0be4ff6a000b60d86d15a8d78e5da5cb249ad489aafb5482d70b7f7c05f4d89dbf794043e1597510fdacf00a5ca4c0772594f47866b1ce901be9606b78887c33";
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    @Override
    public String extraerCorreo(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extraerExpiracion(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return extraerExpiracion(token).before(new Date());
    }

    @Override
    public String generarToken(String correo) {
        Map<String, Object> claims = new HashMap<>();
        return crearToken(claims, correo);
    }

    @Override
    public String crearToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public Boolean validarToken(String token, String correo) {
        final String extractedCorreo = extraerCorreo(token);
        return (extractedCorreo.equals(correo) && !isTokenExpired(token));
    }
}
