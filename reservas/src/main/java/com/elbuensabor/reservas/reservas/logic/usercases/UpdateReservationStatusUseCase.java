package com.elbuensabor.reservas.reservas.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.controllers.converters.EntityConverters;
import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.validators.Result;

@Service
public class UpdateReservationStatusUseCase {

    @Autowired
    private ReservationRepository reservationRepository;

    public Result<ReservationUI> cancel(String reservationId) {
        try {
            var reservationCanceled = reservationRepository.findById(reservationId).get();
            reservationCanceled.setReservationState("CANCELADA");
            reservationRepository.save(reservationCanceled);
            var reservationUI = EntityConverters.ReservationEntityToUI(reservationCanceled);
            return Result.success(reservationUI);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    public Result<ReservationUI> complete(String reservationId) {
        try {
            var reservationComplete = reservationRepository.findById(reservationId).get();
            reservationComplete.setReservationState("COMPLETADA");
            reservationComplete.setTableReserved(1);
            reservationRepository.save(reservationComplete);
            var reservationUI = EntityConverters.ReservationEntityToUI(reservationComplete);
            return Result.success(reservationUI);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    

}
