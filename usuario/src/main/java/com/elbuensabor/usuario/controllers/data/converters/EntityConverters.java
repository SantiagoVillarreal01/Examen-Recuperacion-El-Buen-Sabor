package com.elbuensabor.usuario.controllers.data.converters;

import com.elbuensabor.usuario.controllers.data.entities.UserEntityUI;
import com.elbuensabor.usuario.data.entities.db.UserEntityDb;

public class EntityConverters {

    public static UserEntityUI userEntityDbToUI(UserEntityDb userEntityDb) {
        if (userEntityDb == null) {
            return null;
        }

        UserEntityUI userEntityUI = new UserEntityUI();
        userEntityUI.setId(userEntityDb.getId());
        userEntityUI.setName(userEntityDb.getName());
        userEntityUI.setLastName(userEntityDb.getLastName());
        userEntityUI.setEmail(userEntityDb.getEmail());

        return userEntityUI;
    }

    public static UserEntityDb userEntityUIToDb(UserEntityUI userEntityUI) {
        if (userEntityUI == null) {
            return null;
        }

        UserEntityDb userEntityDb = new UserEntityDb();
        userEntityDb.setId(userEntityUI.getId());
        userEntityDb.setName(userEntityUI.getName());
        userEntityDb.setLastName(userEntityUI.getLastName());
        userEntityDb.setEmail(userEntityUI.getEmail());

        return userEntityDb;
    }
}
