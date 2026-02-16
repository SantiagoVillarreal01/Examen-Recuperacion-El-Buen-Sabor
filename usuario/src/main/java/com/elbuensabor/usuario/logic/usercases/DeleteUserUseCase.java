package com.elbuensabor.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.usuario.data.repository.UserRepository;
import com.elbuensabor.usuario.logic.validators.Result;

@Service
public class DeleteUserUseCase {

    @Autowired
    private UserRepository userRepository;

     public Result<Boolean> delete(String id) {
        try {
            if (!userRepository.existsById(id)) {
                return Result.failure(new Exception("Usuario no existe"));
            }
            userRepository.deleteById(id);
            return Result.success(true);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
