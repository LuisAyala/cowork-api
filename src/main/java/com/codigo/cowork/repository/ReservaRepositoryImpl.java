package com.codigo.cowork.repository;

import com.codigo.cowork.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepositoryImpl implements ReservaRepository{
    private final List<Reserva> reservas = new ArrayList<>();
    private  Long id = 1L;


    @Override
    public List<Reserva> busquedaTodos() {
        return reservas;
    }

    @Override
    public Optional<Reserva> busquedaPorId(Long id) {
        return reservas.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    @Override
    public Reserva salva(Reserva reserva) {
        if (reserva.getId() == null) {
            reserva.setId(id++);
            reservas.add(reserva);
            return reserva;
        } else {
            return busquedaPorId(reserva.getId())
                    .map(existente -> {
                        existente.setEstado(reserva.getEstado());
                        existente.setResponsable(reserva.getResponsable());
                        existente.setEmail(reserva.getEmail());
                        existente.setFecha(reserva.getFecha());
                        existente.setHoraInicio(reserva.getHoraInicio());
                        existente.setHoraFin(reserva.getHoraFin());
                        return existente;
                    })
                    .orElseGet(() -> {
                        reservas.add(reserva);
                        return reserva;
                    });
        }
    }

    @Override
    public void eliminaPorId(Long id) {
        reservas.removeIf(r -> r.getId().equals(id));
    }

    @Override
    public void eliminaPorSalaId(Long salaId) {
        reservas.removeIf(r -> r.getSalaId().equals(salaId));
    }
}
