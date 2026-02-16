package com.elbuensabor.reservas.reservas.logic.usercases;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.controllers.converters.EntityConverters;
import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.entities.db.ReservationEntityDB;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.network.interfaces.UserInterface;
import com.elbuensabor.reservas.reservas.logic.validators.Result;
import com.elbuensabor.reservas.reservas.logic.validators.UUIDReservers;

@Service
public class MakeReservationUseCase {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserInterface userInterface;

    public Result<ReservationUI> create( String userId, String reservationDateString, int people) {

        try {
            var userResult = userInterface.findById(userId);
            String reservationId = UUIDReservers.generateRandomCode();
            Date reservationDate = Date.valueOf(reservationDateString);
            var reservationBuilder = ReservationEntityDB.builder()
                    .reservationId("RV-" + reservationId)
                    .userName(userResult.name + " " + userResult.lastName)
                    .reservationDate(reservationDate)
                    .reservationState("PENDIENTE")
                    .tableReserved(-1)
                    .peopleCount(people)
                    .build();
            var saved = reservationRepository.save(reservationBuilder);
            return Result.success(EntityConverters.ReservationEntityToUI(saved));

        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
