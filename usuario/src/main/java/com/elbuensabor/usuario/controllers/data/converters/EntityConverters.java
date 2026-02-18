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
        userEntityUI.setName(userEntityDb.getNameUser());
        userEntityUI.setLastName(userEntityDb.getLastNameUser());
        userEntityUI.setEmail(userEntityDb.getEmailUser());

        return userEntityUI;
    }

    public static UserEntityDb userEntityUIToDb(UserEntityUI userEntityUI) {
        if (userEntityUI == null) {
            return null;
        }

        UserEntityDb userEntityDb = new UserEntityDb();
        userEntityDb.setId(userEntityUI.getId()); 
        userEntityDb.setNameUser(userEntityUI.getName());
        userEntityDb.setLastNameUser(userEntityUI.getLastName());
        userEntityDb.setEmailUser(userEntityUI.getEmail());

        return userEntityDb;
    }
}
