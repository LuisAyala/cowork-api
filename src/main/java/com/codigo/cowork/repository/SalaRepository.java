package com.codigo.cowork.repository;

import com.codigo.cowork.model.Sala;

import java.util.List;
import java.util.Optional;

public interface SalaRepository {
    List<Sala> buscaTodas();
    Optional<Sala> buscaPorId(Long id);
    Sala salvar(Sala sala);
    boolean eliminaPorId(Long id);
}
