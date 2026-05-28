package com.codigo.cowork.service;

import com.codigo.cowork.dto.SalaRequestDTO;
import com.codigo.cowork.dto.SalaResponseDTO;
import com.codigo.cowork.mapper.SalaMapper;
import com.codigo.cowork.model.Sala;
import com.codigo.cowork.repository.ReservaRepository;
import com.codigo.cowork.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {
    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository; // Requerido para la eliminación en cascada

    // constructor
    public SalaService(SalaRepository salaRepository, ReservaRepository reservaRepository) {
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
    }

    public List<SalaResponseDTO> listarTodas() {
        return salaRepository.buscaTodas().stream()
                .map(SalaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public SalaResponseDTO obtenerPorId(Long id) {
        Sala sala = salaRepository.buscaPorId(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada con ID: " + id +"!"));
        return SalaMapper.toResponseDTO(sala);
    }

    public SalaResponseDTO crear(SalaRequestDTO dto) {
        Sala sala = SalaMapper.toModel(dto);
        Sala salaGuardada = salaRepository.salvar(sala);
        return SalaMapper.toResponseDTO(salaGuardada);
    }

    public SalaResponseDTO actualizar(Long id, SalaRequestDTO dto) {
        Sala salaExistente = salaRepository.buscaPorId(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada para actualizar!"));

        salaExistente.setCodigo(dto.codigo());
        salaExistente.setNombre(dto.nombre());
        salaExistente.setCapacidad(dto.capacidad());
        salaExistente.setUbicacion(dto.ubicacion());
        if (dto.activa() != null) {
            salaExistente.setActiva(dto.activa());
        }

        Sala salaActualizada = salaRepository.salvar(salaExistente);
        return SalaMapper.toResponseDTO(salaActualizada);
    }

    public void eliminar(Long id) {

        reservaRepository.eliminaPorSalaId(id);  // Primero: Eliminar las reservas asociadas

        boolean eliminado = salaRepository.eliminaPorId(id);
        if (!eliminado) {
            throw new RuntimeException("No se pudo eliminar, sala no encontrada!");
        }
    }
}
