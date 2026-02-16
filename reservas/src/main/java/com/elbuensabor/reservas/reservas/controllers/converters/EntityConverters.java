package com.elbuensabor.reservas.reservas.controllers.converters;

import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.entities.db.ReservationEntityDB;

public class EntityConverters {

    public static ReservationUI ReservationEntityToUI(ReservationEntityDB reservationEntity) {
        return new ReservationUI(
                reservationEntity.getReservationId(),
                reservationEntity.getUserName(),
                reservationEntity.getReservationDate(),
                reservationEntity.getReservationState(),
                reservationEntity.getTableReserved(),
                reservationEntity.getPeopleCount());
    }
}
