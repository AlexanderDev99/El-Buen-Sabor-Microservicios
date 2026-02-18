package com.elbuensabor.reservas.reservas.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elbuensabor.reservas.reservas.controllers.converters.ResultAPI;
import com.elbuensabor.reservas.reservas.logic.usercases.EstateReservationsUserCase;
import com.elbuensabor.reservas.reservas.logic.usercases.GetAllReservationsUserCase;
import com.elbuensabor.reservas.reservas.logic.usercases.GetReservationUserCase;
import com.elbuensabor.reservas.reservas.logic.usercases.MakeReservationUserCase;
import com.elbuensabor.reservas.reservas.logic.usercases.ModifyReservationUserCase;

@RestController
@RequestMapping("/api/reservations")
public class ReservationService {

    @Autowired
    private MakeReservationUserCase makeReservationUserCase;

    @Autowired
    private GetAllReservationsUserCase getAllReservationsCase;

    @Autowired
    GetReservationUserCase getReservationInfo;

    @Autowired
    private EstateReservationsUserCase stateReservationCase;

    @Autowired
    private ModifyReservationUserCase modifyReservationCase;

    // Crear reservas
    @GetMapping("/make-reservations")
    public ResultAPI makeReservation(
            @RequestParam("name") String userName,
            @RequestParam("date") String dateReservationString,
            @RequestParam("people") int numberOfGuests) {

        var reserva = makeReservationUserCase.execute(userName, dateReservationString, numberOfGuests);

        return reserva.fold(
                val -> new ResultAPI(val.getReservationId().toString()),
                ex -> new ResultAPI(ex.getMessage()));
    }

    // Mostrar todas las reservas
    @GetMapping("/all-reservations")
    public ResultAPI getAllReservations() {
        return getAllReservationsCase.getAllReservations().fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    // Mostrar todas las reservas con paginacion
    @GetMapping("/all-reservations/{page}")
    public ResultAPI getAllReservations(@PathVariable("page") int page) {
        return getAllReservationsCase.getAllReservationsPagginResult(page).fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    // Mostrar una reserva por ID
    @GetMapping("/get-reservation/{reservationId}")
    public ResultAPI getReservation(@PathVariable("reservationId") String reservationId) {
        return getReservationInfo.execute(reservationId).fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    // Cancelar una reserva por ID
    @PutMapping("/cancel-reservation/{reservationId}")
    public ResultAPI cancelReservation(@PathVariable("reservationId") String reservationId) {
        return stateReservationCase.calcelReservation(reservationId).fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    // Completar reserva por ID
    @PutMapping("/complete-reservation/{reservationId}")
    public ResultAPI completeReservation(@PathVariable("reservationId") String reservationId) {
        return stateReservationCase.completeReservation(reservationId).fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    // Modificar una reserva por ID
    @PutMapping("/modify-reservation/{reservationId}")
    public ResultAPI modifyReservation(
            @PathVariable("reservationId") String reservationId,
            @RequestParam("name") String newUserName,
            @RequestParam("date") String newDateString,
            @RequestParam("state") String newState,
            @RequestParam("table") int newMesaReservada,
            @RequestParam("people") int newPeopleCount) {

        return modifyReservationCase.execute(reservationId, newUserName, java.sql.Date.valueOf(newDateString), newState,
                newMesaReservada, newPeopleCount).fold(
                        val -> new ResultAPI(val),
                        ex -> new ResultAPI(ex.getMessage()));  
    }

}
