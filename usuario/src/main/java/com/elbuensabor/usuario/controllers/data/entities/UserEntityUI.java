package com.elbuensabor.usuario.controllers.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityUI {

    private String id;
    private String name;
    private String lastName;
    private String email;

}
