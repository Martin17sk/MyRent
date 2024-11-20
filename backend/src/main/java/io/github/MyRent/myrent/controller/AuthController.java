package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.model.AuthRequestDTO;
import io.github.MyRent.myrent.model.AuthResponseDTO;
import io.github.MyRent.myrent.service.impl.JwtUtilImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtilImpl jwtUtil = new JwtUtilImpl();
    private final AuthenticationManager authenticationManager;
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    // region Endpoints
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getCorreo(), authRequest.getContrasenia())
        );

        String token = jwtUtil.generarToken(authRequest.getCorreo());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token,@RequestHeader("correo") String correo) {
        token = token.replace("Bearer ", "");

        if (jwtUtil.validarToken(token, correo)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(403).body("Token is invalid or expired");
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestHeader("Authorization") String token,@RequestHeader("correo") String correo) {
        token = token.replace("Bearer ", "");

        if (jwtUtil.validarToken(token, correo)) {
            String nuevoToken = jwtUtil.generarToken(correo);
            return ResponseEntity.ok(nuevoToken);
        } else {
            return ResponseEntity.status(403).body("Invalid or expired refresh token");
        }
    }

    // endregion
}
