package com.codigo.cowork.controller;

import com.codigo.cowork.dto.ReservaRequestDTO;
import com.codigo.cowork.dto.ReservaResponseDTO;
import com.codigo.cowork.service.ReservaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }


    @GetMapping("/info")
    public Map<String, String> obtenerInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("nombre_aplicacion", "cowork-api");
        info.put("version", "1.0.0");
        info.put("autor", "Luis Ayala");
        return info;
    }

    // Crear reserva (Retorna 201 Created)
    @PostMapping("/reservas")
    public ResponseEntity<ReservaResponseDTO> crear(@RequestBody ReservaRequestDTO dto) {
        ReservaResponseDTO creada = reservaService.crear(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    // Obtener reserva por ID
    @GetMapping("/reservas/{id}")
    public ReservaResponseDTO obtenerPorId(@PathVariable Long id) {
        return reservaService.obtenerPorId(id);
    }

    // Listado con filtros
    @GetMapping("/reservas")
    public List<ReservaResponseDTO> listarConFiltros(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
            @RequestParam(required = false) Long salaId) {
        return reservaService.listarConFiltros(estado, fecha, salaId);
    }

    // Listar todas las reservas de una sala específica
    @GetMapping("/reservas/sala/{salaId}")
    public List<ReservaResponseDTO> listarPorSala(@PathVariable Long salaId) {
        return reservaService.listarPorSala(salaId);
    }

    // Cambiar el estado de una reserva
    @PutMapping("/reservas/{id}/estado")
    public ReservaResponseDTO cambiarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        return reservaService.cambiarEstado(id, nuevoEstado);
    }

    // Eliminar una reserva (Retorna 204 No Content)
    @DeleteMapping("/reservas/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
