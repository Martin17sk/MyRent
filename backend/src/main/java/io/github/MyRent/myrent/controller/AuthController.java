package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.dto.AuthRequestDTO;
import io.github.MyRent.myrent.dto.AuthResponseDTO;
import io.github.MyRent.myrent.dto.UsuarioDTO;
import io.github.MyRent.myrent.service.UsuarioService;
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
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
    }
    // region Endpoints
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getCorreo(), authRequest.getContrasenia())
        );

        String token = jwtUtil.generarToken(authRequest.getCorreo());
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorCorreo(authRequest.getCorreo());
        AuthResponseDTO response = new AuthResponseDTO(token, usuarioDTO.getCorreo(), usuarioDTO.getId());

        return ResponseEntity.ok(response);
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
