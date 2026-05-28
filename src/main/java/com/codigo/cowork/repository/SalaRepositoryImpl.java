package com.codigo.cowork.repository;

import com.codigo.cowork.model.Sala;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SalaRepositoryImpl implements SalaRepository{


    private final List<Sala> salas = new ArrayList<>();
    private Long id = 1L;

    @Override
    public List<Sala> buscaTodas() {
        return salas;
    }

    @Override
    public Optional<Sala> buscaPorId(Long id) {
        return salas.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public Sala salvar(Sala sala) {
        if (sala.getId() == null) {  // Si es nueva sala? Le asignamos un nuevo ID único
            sala.setId(id++);
            salas.add(sala);
            return sala;
        } else {
            // Si ya tiene ID, buscamos y actualizamos sus valores
            return buscaPorId(sala.getId())
                    .map(existente -> {
                        existente.setCodigo(sala.getCodigo());
                        existente.setNombre(sala.getNombre());
                        existente.setCapacidad(sala.getCapacidad());
                        existente.setUbicacion(sala.getUbicacion());
                        existente.setActiva(sala.isActiva());
                        return existente;
                    })
                    .orElseGet(() -> {
                        salas.add(sala);
                        return sala;
                    });
        }
    }

    @Override
    public boolean eliminaPorId(Long id) {
        return salas.removeIf (s -> s.getId().equals(id));
    }
}
