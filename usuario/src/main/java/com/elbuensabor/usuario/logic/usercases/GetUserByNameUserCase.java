package com.elbuensabor.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.usuario.controllers.data.converters.EntityConverters;
import com.elbuensabor.usuario.controllers.data.entities.UserEntityUI;
import com.elbuensabor.usuario.data.repository.UserRepository;
import com.elbuensabor.usuario.logic.validators.Result;

@Service
public class GetUserByNameUserCase {

    @Autowired
    private UserRepository userRepository;

    public Result<UserEntityUI> execute(String nameUser) {
        try {
            var userOptional = userRepository.findByNameUser(nameUser);
            if (userOptional.isPresent()) {
                return Result.success(EntityConverters.userEntityDbToUI(userOptional.get()));
            } else {
                return Result.failure(new Exception("User not found with name: " + nameUser));
            }
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
