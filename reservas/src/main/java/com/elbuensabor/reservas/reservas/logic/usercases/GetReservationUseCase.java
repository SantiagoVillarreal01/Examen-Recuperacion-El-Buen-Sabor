package com.elbuensabor.reservas.reservas.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.controllers.converters.EntityConverters;
import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.validators.Result;

@Service
public class GetReservationUseCase {

    @Autowired
    private ReservationRepository reservationRepository;

    public Result<ReservationUI> get(String reservationId) {
        try {
            var reservation = reservationRepository.findById(reservationId).get();
            var reservationUI = EntityConverters.ReservationEntityToUI(reservation);

            return Result.success(reservationUI);

        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
