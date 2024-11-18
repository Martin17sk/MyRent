package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.model.AuthRequestDTO;
import io.github.MyRent.myrent.model.AuthResponseDTO;
import io.github.MyRent.myrent.service.impl.JwtUtilImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    // endregion
}
