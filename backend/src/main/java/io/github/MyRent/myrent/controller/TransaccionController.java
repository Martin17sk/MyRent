package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.dto.TransaccionDTO;
import io.github.MyRent.myrent.service.ITransaccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transacciones")
public class TransaccionController {

    private final ITransaccionService transaccionService;

    public TransaccionController(ITransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionDTO> getTransaccionById(@PathVariable Long id) {
        TransaccionDTO transaccion = transaccionService.findById(id);
        return ResponseEntity.ok(transaccion);
    }

    @GetMapping("/usuarios/{id_usuario}")
    public ResponseEntity<List<TransaccionDTO>> getTransaccionesByUsuarioId(@PathVariable Long id_usuario, @RequestParam(value = "tipo", required = false) String tipo) {
        List<TransaccionDTO> transacciones = tipo != null ? transaccionService.getTransaccionesByUsuarioIdAndTipo(id_usuario, tipo) : transaccionService.getTransaccionesByUsuarioId(id_usuario);
        return ResponseEntity.ok(transacciones);
    }

    @GetMapping("/perfiles/{id_perfil}")
    public ResponseEntity<List<TransaccionDTO>> getTransaccionesByPerfilId(@PathVariable Long id_perfil, @RequestParam(value = "tipo", required = false) String tipo) {
        List<TransaccionDTO> transacciones = tipo != null ? transaccionService.getTransaccionesByPerfilIdAndTipo(id_perfil, tipo) : transaccionService.getTransaccionesByPerfilId(id_perfil);
        return ResponseEntity.ok(transacciones);
    }

    @GetMapping("/propiedades/{id_propiedad}")
    public ResponseEntity<List<TransaccionDTO>> getTransaccionesByPropiedadId(@PathVariable Long id_propiedad, @RequestParam(value = "tipo", required = false) String tipo) {
        List<TransaccionDTO> transacciones = tipo != null ? transaccionService.getTransaccionesByPropiedadIdAndTipo(id_propiedad, tipo) : transaccionService.getTransaccionesByPropiedadId(id_propiedad);
        return ResponseEntity.ok(transacciones);
    }

    @PostMapping
    public ResponseEntity<TransaccionDTO> postTransaccion(@RequestBody TransaccionDTO transaccion) {
        TransaccionDTO transaccionGuardada = transaccionService.save(transaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaccionGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaccion(@PathVariable Long id) {
        transaccionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
