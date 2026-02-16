package com.elbuensabor.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.usuario.controllers.data.converters.EntityConverters;
import com.elbuensabor.usuario.controllers.data.entities.UserEntityUI;
import com.elbuensabor.usuario.data.entities.db.UserEntityDb;
import com.elbuensabor.usuario.data.repository.UserRepository;
import com.elbuensabor.usuario.logic.validators.Result;
import com.elbuensabor.usuario.logic.validators.UUIDUsers;

@Service
public class RegisterUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public Result<UserEntityUI> register(String name, String lastName, String email, String password) {
        try {
            String userId = UUIDUsers.generateRandomCode();
            
            var userBuilder = UserEntityDb.builder()
                    .id("US-" + userId)
                    .name(name != null && !name.isEmpty() ? name : "NoAplica")
                    .lastName(lastName != null && !lastName.isEmpty() ? lastName : "NoAplica")
                    .email(email != null && !email.isEmpty() ? email : "NoAplica")
                    .password(password) 
                    .build();

            var userSaved = userRepository.save(userBuilder);
            return Result.success(EntityConverters.userEntityDbToUI(userSaved));

        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
