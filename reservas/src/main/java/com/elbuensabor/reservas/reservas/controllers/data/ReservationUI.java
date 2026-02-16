package com.elbuensabor.reservas.reservas.controllers.data;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUI {

    private String reservationId;
    private String userName;
    private Date reservationDate;
    private String reservationState;
    private int tableReserved;
    private int peopleCount;
}
