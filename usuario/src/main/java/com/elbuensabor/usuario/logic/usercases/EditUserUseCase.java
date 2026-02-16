package com.elbuensabor.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.usuario.controllers.data.converters.EntityConverters;
import com.elbuensabor.usuario.controllers.data.entities.UserEntityUI;
import com.elbuensabor.usuario.data.entities.db.UserEntityDb;
import com.elbuensabor.usuario.data.repository.UserRepository;
import com.elbuensabor.usuario.logic.validators.Result;

@Service
public class EditUserUseCase {

    @Autowired
    private UserRepository userRepository;


    public Result<UserEntityUI> update(String id, String name, String lastName, String email) {
        try {
            return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(name != null ? name : "NoAplica");
                    existingUser.setLastName(lastName != null ? lastName : "NoAplica");
                    existingUser.setEmail(email != null ? email : "NoAplica");
                    
                    UserEntityDb savedUser = userRepository.save(existingUser);
                    
                    return Result.success(EntityConverters.userEntityDbToUI(savedUser));
                })
                .orElseGet(() -> Result.failure(new Exception("Usuario no encontrado con ID: " + id)));
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
