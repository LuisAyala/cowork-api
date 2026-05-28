package com.codigo.cowork.controller;

import com.codigo.cowork.dto.SalaRequestDTO;
import com.codigo.cowork.dto.SalaResponseDTO;
import com.codigo.cowork.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {
    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    // GET /api/salas - Listar todas las salas (Retorna 200 OK directamente)
    @GetMapping
    public List<SalaResponseDTO> listarTodas() {
        return salaService.listarTodas();
    }

    // GET /api/salas/{id} - Obtener una sala por ID
    @GetMapping("/{id}")
    public SalaResponseDTO obtenerPorId(@PathVariable Long id) {
        return salaService.obtenerPorId(id);
    }

    // POST /api/salas - Crear una nueva sala (Retorna ResponseEntity con 201 Created)
    @PostMapping
    public ResponseEntity<SalaResponseDTO> crear(@RequestBody SalaRequestDTO dto) {
        SalaResponseDTO creada = salaService.crear(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    // PUT /api/salas/{id} - Actualizar una sala existente
    @PutMapping("/{id}")
    public SalaResponseDTO actualizar(@PathVariable Long id, @RequestBody SalaRequestDTO dto) {
        return salaService.actualizar(id, dto);
    }

    // DELETE /api/salas/{id} - Eliminar una sala (Retorna ResponseEntity con 204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        salaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
