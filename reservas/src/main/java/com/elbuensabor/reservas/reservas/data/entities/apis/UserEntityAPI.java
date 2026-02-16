package com.elbuensabor.reservas.reservas.data.entities.apis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityAPI {

    public String id;
    public String name;
    public String lastName;
    public String email;
}
