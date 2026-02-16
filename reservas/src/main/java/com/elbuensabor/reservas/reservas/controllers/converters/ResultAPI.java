package com.elbuensabor.reservas.reservas.controllers.converters;

import lombok.Data;

@Data
public class ResultAPI {
    String status;
    Object result;

    public ResultAPI(Object result) {
        this.status = "Success 200 Ok";
        this.result = result;
    }

    public ResultAPI(String status) {
        this.status = status;
        this.result = null;
    }
}
