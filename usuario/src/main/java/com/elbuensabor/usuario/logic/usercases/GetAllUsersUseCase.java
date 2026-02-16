package com.elbuensabor.usuario.logic.usercases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.usuario.controllers.data.converters.EntityConverters;
import com.elbuensabor.usuario.controllers.data.entities.UserEntityUI;
import com.elbuensabor.usuario.data.entities.db.UserEntityDb;
import com.elbuensabor.usuario.data.repository.UserRepository;
import com.elbuensabor.usuario.logic.validators.Result;

@Service
public class GetAllUsersUseCase {

    @Autowired
    private UserRepository userRepository;

    public Result<List<UserEntityUI>> getAll() {
        List<UserEntityUI> usersUI = new ArrayList<UserEntityUI>();
        try {
            List<UserEntityDb> users = userRepository.findAll();
            users.forEach(it -> usersUI.add(EntityConverters.userEntityDbToUI(it)));
            return Result.success(usersUI);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
