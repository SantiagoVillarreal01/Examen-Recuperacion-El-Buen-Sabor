package com.elbuensabor.usuario.data.entities.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDb {

    @Id
    private String id;
    
    private String name;
    private String lastName;
    private String email;
    private String password;
}
