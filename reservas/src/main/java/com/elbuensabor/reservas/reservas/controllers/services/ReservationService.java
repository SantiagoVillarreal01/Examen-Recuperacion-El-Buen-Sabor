package com.elbuensabor.reservas.reservas.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elbuensabor.reservas.reservas.controllers.converters.ResultAPI;
import com.elbuensabor.reservas.reservas.controllers.rabbit.RabbitMessage;
import com.elbuensabor.reservas.reservas.logic.usercases.GetAllReservationsUseCase;
import com.elbuensabor.reservas.reservas.logic.usercases.GetReservationUseCase;
import com.elbuensabor.reservas.reservas.logic.usercases.MakeReservationUseCase;
import com.elbuensabor.reservas.reservas.logic.usercases.ModifyReservationUseCase;
import com.elbuensabor.reservas.reservas.logic.usercases.UpdateReservationStatusUseCase;

@RestController
@RequestMapping("/api/reserva")
public class ReservationService {

    @Autowired
    private MakeReservationUseCase makeReservation;

    @Autowired
    private GetAllReservationsUseCase getAllReservations;

    @Autowired
    private GetReservationUseCase getReservationById;

    @Autowired
    private UpdateReservationStatusUseCase stateReservation;

    @Autowired
    private ModifyReservationUseCase modifyReservation;

    @Autowired
    private RabbitMessage rabbitMessage;

    @GetMapping("/create")
    public ResultAPI makeReservation(
            @RequestParam("userId") String userId,
            @RequestParam("date") String reservationDateString,
            @RequestParam("people") int peopleCount) {

        rabbitMessage.sendLog("/api/reserva/create");
        var reserva = makeReservation.create(userId, reservationDateString, peopleCount);
        return reserva.fold(
                val -> new ResultAPI(val.getReservationId()),
                ex -> new ResultAPI(ex.getMessage()));
    }

    @GetMapping("/all")
    public ResultAPI getAllReservations() {
        rabbitMessage.sendLog("/api/reserva/all");
        return getAllReservations.getAll().fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    @GetMapping("/all/{page}")
    public ResultAPI getAllReservations(@PathVariable("page") int page) {
        rabbitMessage.sendLog("/api/reserva/all/" + page);
        return getAllReservations.getAllReservationsPaggin(page).fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    @GetMapping("/search/{reservationId}")
    public ResultAPI getReservation(@PathVariable("reservationId") String reservationId) {
        rabbitMessage.sendLog("/api/reserva/search/" + reservationId);
        return getReservationById.get(reservationId).fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    @GetMapping("/cancel/{reservationId}")
    public ResultAPI cancelReservation(@PathVariable("reservationId") String reservationId) {
        rabbitMessage.sendLog("/api/reserva/cancel/" + reservationId);
        return stateReservation.cancel(reservationId).fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    @GetMapping("/complete/{reservationId}")
    public ResultAPI completeReservation(@PathVariable("reservationId") String reservationId) {
        rabbitMessage.sendLog("/api/reserva/complete/" + reservationId);
        return stateReservation.complete(reservationId).fold(
                val -> new ResultAPI(val),
                ex -> new ResultAPI(ex.getMessage()));
    }

    @GetMapping("/update/{reservationId}")
    public ResultAPI modifyReservation(
            @PathVariable("reservationId") String reservationId,
            @RequestParam("name") String newUserName,
            @RequestParam("date") String newDateString,
            @RequestParam("state") String newState,
            @RequestParam("table") int newTableReserved,
            @RequestParam("people") int newPeopleCount) {
        rabbitMessage.sendLog("/api/reserva/update/" + reservationId);
        return modifyReservation.update(reservationId, newUserName, java.sql.Date.valueOf(newDateString), newState,
                newTableReserved, newPeopleCount).fold(
                        val -> new ResultAPI(val),
                        ex -> new ResultAPI(ex.getMessage()));
    }

}
