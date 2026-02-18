package com.elbuensabor.reservas.reservas.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.controllers.converters.EntityConvertes;
import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.validators.Result;

@Service
public class GetReservationUserCase {

    @Autowired
    private ReservationRepository reservationRepository;

    public Result<ReservationUI> execute(String reservationId) {
        Result<ReservationUI> result = null;
        try {
            var reservations = reservationRepository.findByReservationId(reservationId);
            var reservationUI = EntityConvertes.ReservationEntityToUI(reservations);
            result = Result.success(reservationUI);
        } catch (Exception e) {
            result = Result.failure(e);
        }
        return result;
    }

}
