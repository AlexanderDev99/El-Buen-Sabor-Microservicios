package com.elbuensabor.reservas.reservas.logic.usercases;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.data.entities.db.ReservationEntityDB;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.network.interfaces.UsersInterface;
import com.elbuensabor.reservas.reservas.logic.validators.Result;
import com.elbuensabor.reservas.reservas.logic.validators.UUIDReservers;

@Service
public class MakeReservationUserCase {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UsersInterface usersInterface;

    public Result<ReservationEntityDB> execute(
            String userId,
            String dateReservationString,
            int numberOfGuests) {

        Result<ReservationEntityDB> result = null;

        try {
            var userResult = usersInterface.GetInfoUsuario(Integer.parseInt(userId));

            Date dateReservation = Date.valueOf(dateReservationString);
            var reservaBuilder = ReservationEntityDB.builder()
                    .userName(userResult.name + " " + userResult.lastName)
                    .dateReservation(dateReservation)
                    .estateReservation("PENDING")
                    .reservedTable(-1)
                    .numberOfGuests(numberOfGuests);
            reservaBuilder.reservationId(UUIDReservers.generateRandomCode());
            var reservation = reservaBuilder.build();

            // Insertar la reserva en la base de datos
            var reservationSaved = reservationRepository.save(reservation);
            result = Result.success(reservationSaved);

        } catch (Exception e) {
            result = Result.failure(e);
        }
        return result;
    }

}
