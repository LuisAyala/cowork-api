package com.codigo.cowork.service;

import com.codigo.cowork.dto.ReservaRequestDTO;
import com.codigo.cowork.dto.ReservaResponseDTO;
import com.codigo.cowork.mapper.ReservaMapper;
import com.codigo.cowork.model.Reserva;
import com.codigo.cowork.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public ReservaResponseDTO crear(ReservaRequestDTO dto) {
        Reserva reserva = ReservaMapper.toModel(dto);
        Reserva guardada = reservaRepository.salva(reserva);
        return ReservaMapper.toResponseDTO(guardada);
    }

    public ReservaResponseDTO obtenerPorId(Long id) {
        Reserva reserva = reservaRepository.busquedaPorId(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
        return ReservaMapper.toResponseDTO(reserva);
    }

    // vienen o no nulos
    public List<ReservaResponseDTO> listarConFiltros(String estado, LocalDate fecha, Long salaId) {
        return reservaRepository.busquedaTodos().stream()
                .filter(r -> estado == null || r.getEstado().equalsIgnoreCase(estado))
                .filter(r -> fecha == null || r.getFecha().equals(fecha))
                .filter(r -> salaId == null || r.getSalaId().equals(salaId))
                .map(ReservaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ReservaResponseDTO> listarPorSala(Long salaId) {
        return reservaRepository.busquedaTodos().stream()
                .filter(r -> r.getSalaId().equals(salaId))
                .map(ReservaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Validación de cambio de estado
    public ReservaResponseDTO cambiarEstado(Long id, String nuevoEstado) {
        if (nuevoEstado == null ||
                (!nuevoEstado.equals("PENDIENTE") && !nuevoEstado.equals("CONFIRMADA") && !nuevoEstado.equals("CANCELADA"))) {
            throw new RuntimeException("Estado inválido. Solo se permite: PENDIENTE, CONFIRMADA o CANCELADA.");
        }

        Reserva reserva = reservaRepository.busquedaPorId(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(nuevoEstado);
        Reserva actualizada = reservaRepository.salva(reserva);
        return ReservaMapper.toResponseDTO(actualizada);
    }

    public void eliminar(Long id) {
        reservaRepository.eliminaPorId(id);
    }

}
