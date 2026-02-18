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
public class GetAllUserUserCase {

    @Autowired
    private UserRepository userRepository;

    public Result<List<UserEntityUI>> execute() {
        List<UserEntityUI> usUI = new ArrayList<UserEntityUI>();
        try {
            List<UserEntityDb> users = userRepository.findAll();
            users.forEach(it -> usUI.add(EntityConverters.userEntityDbToUI(it)));
            return Result.success(usUI);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
