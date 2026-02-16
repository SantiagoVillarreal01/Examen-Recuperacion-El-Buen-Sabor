package com.elbuensabor.reservas.reservas.data.entities.db;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations")
public class ReservationEntityDB {

    @Id
    private String reservationId;

    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "reservation_state")
    private String reservationState;

    @Column(name = "table_reserved")
    private int tableReserved;

    @Column(name = "people_count")
    private int peopleCount;

}
