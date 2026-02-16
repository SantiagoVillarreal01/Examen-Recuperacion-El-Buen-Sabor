package com.elbuensabor.reservas.reservas.data.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elbuensabor.reservas.reservas.data.entities.db.ReservationEntityDB;

public interface ReservationRepository extends JpaRepository<ReservationEntityDB, String> {

    @Query("SELECT r FROM ReservationEntityDB r")
    List<ReservationEntityDB> findAllPaging(Pageable pageable);
}
