package com.elbuensabor.reservas.reservas.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elbuensabor.reservas.reservas.data.entities.db.ReservationEntityDB;

public interface ReservationRepository
        extends JpaRepository<ReservationEntityDB, Integer> {

    @Query("SELECT r FROM ReservationEntityDB r WHERE r.reservationId = ?1")
    public ReservationEntityDB findByReservationId(String reservationId);

    @Query("SELECT r FROM ReservationEntityDB r")
    public List<ReservationEntityDB> findAllPaging(Pageable pageable);
}
