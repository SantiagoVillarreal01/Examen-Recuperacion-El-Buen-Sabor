package com.elbuensabor.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.usuario.controllers.data.converters.EntityConverters;
import com.elbuensabor.usuario.controllers.data.entities.UserEntityUI;
import com.elbuensabor.usuario.data.repository.UserRepository;
import com.elbuensabor.usuario.logic.validators.Result;

@Service
public class GetUserByIdUseCase {

    @Autowired
    private UserRepository userRepository;

    public Result<UserEntityUI> get(String id) {
        try {
            var userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                return Result.success(EntityConverters.userEntityDbToUI(userOptional.get()));
            } else {
                return Result.failure(new Exception("Usuario con ID " + id + " no encontrado"));
            }
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
