package com.elbuensabor.usuario.data.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.elbuensabor.usuario.data.entities.db.UserEntityDb;

public interface UserRepository extends MongoRepository<UserEntityDb, String> {

        

}
