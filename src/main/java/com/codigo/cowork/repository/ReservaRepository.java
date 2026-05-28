package com.codigo.cowork.repository;

import com.codigo.cowork.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository {

    List<Reserva> busquedaTodos();
    Optional<Reserva> busquedaPorId(Long id);
    Reserva salva(Reserva reserva);
    void eliminaPorId(Long id);
    void eliminaPorSalaId(Long salaId); // necesario  para la cascada manual
}
