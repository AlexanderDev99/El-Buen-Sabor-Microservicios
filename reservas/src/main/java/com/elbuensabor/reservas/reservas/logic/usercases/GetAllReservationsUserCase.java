package com.elbuensabor.reservas.reservas.logic.usercases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.controllers.converters.EntityConvertes;
import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.validators.Result;

@Service
public class GetAllReservationsUserCase {

    @Autowired
    private ReservationRepository reservationRepository;

    public Result<List<ReservationUI>> getAllReservations() {

        Result<List<ReservationUI>> result = null;
        List<ReservationUI> reservationsUI = new ArrayList<>();

        try {
            var reservations = reservationRepository.findAll();
            reservations.forEach(r -> reservationsUI.add(
                    EntityConvertes.ReservationEntityToUI(r)));
            result = Result.success(reservationsUI);
        } catch (Exception e) {
            result = Result.failure(e);
        }
        return result;
    }

    public Result<List<ReservationUI>> getAllReservationsPagginResult(int page) {
        Result<List<ReservationUI>> result = null;
        List<ReservationUI> reservationsUI = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page, 5);
            var reservations = reservationRepository.findAllPaging(pageable);
            reservations.forEach(r -> reservationsUI.add(
                    EntityConvertes.ReservationEntityToUI(r)));
            result = Result.success(reservationsUI);
        } catch (Exception e) {
            result = Result.failure(e);
        }
        return result;
    }

}
