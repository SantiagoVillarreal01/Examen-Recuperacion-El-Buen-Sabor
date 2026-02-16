package com.elbuensabor.reservas.reservas.logic.usercases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.controllers.converters.EntityConverters;
import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.validators.Result;

@Service
public class GetAllReservationsUseCase {

    @Autowired
    private ReservationRepository reservationRepository;

    public Result<List<ReservationUI>> getAll() {
        List<ReservationUI> reservationsUI = new ArrayList<>();
        try {
            var reservations = reservationRepository.findAll();
            reservations.forEach(r -> reservationsUI.add(
                    EntityConverters.ReservationEntityToUI(r)));
            return Result.success(reservationsUI);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    public Result<List<ReservationUI>> getAllReservationsPaggin(int page) {
        List<ReservationUI> reservationsUI = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page, 5);
            var reservations = reservationRepository.findAllPaging(pageable);
            reservations.forEach(r -> reservationsUI.add(
                    EntityConverters.ReservationEntityToUI(r)));
            return Result.success(reservationsUI);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
