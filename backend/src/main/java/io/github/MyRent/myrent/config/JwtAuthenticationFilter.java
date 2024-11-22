package io.github.MyRent.myrent.config;

import io.github.MyRent.myrent.service.JwtUtil;

import io.github.MyRent.myrent.service.impl.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil; // Clase que valida y genera JWT
    private final MyUserDetailsService myUserDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, MyUserDetailsService myUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String correo;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // No hay token, continúa con la cadena de filtros
        }

        jwt = authHeader.substring(7); // Quitar "Bearer "
        correo = jwtUtil.extraerCorreo(jwt); // Extrae el correo del token

        // Verificar que el usuario no esté autenticado ya
        if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(correo);

            // Validar el token
            if (jwtUtil.validarToken(jwt, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken); // Configura la autenticación
            }
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
