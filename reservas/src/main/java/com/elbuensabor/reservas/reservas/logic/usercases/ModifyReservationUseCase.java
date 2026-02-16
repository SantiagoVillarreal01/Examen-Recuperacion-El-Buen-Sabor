package com.elbuensabor.reservas.reservas.logic.usercases;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.controllers.converters.EntityConverters;
import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.validators.Result;

@Service
public class ModifyReservationUseCase {

    @Autowired
    private ReservationRepository reservationRepository;

    public Result<ReservationUI> update(String reservationId, String newUserName, 
        Date newDate, String newState, int newTableReserved, int newPeopleCount) {

        try{
            var reservation = reservationRepository.findById(reservationId).get();
            reservation.setUserName(newUserName);
            reservation.setReservationDate(newDate);
            reservation.setReservationState(newState);
            reservation.setTableReserved(newTableReserved);
            reservation.setPeopleCount(newPeopleCount);
            reservationRepository.save(reservation);
            var reservaUI = EntityConverters.ReservationEntityToUI(reservation);
            return Result.success(reservaUI);

        }catch(Exception e){
            return Result.failure(e);
    }

    }

}
