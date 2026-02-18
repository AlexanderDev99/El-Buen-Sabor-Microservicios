package com.elbuensabor.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.usuario.controllers.data.converters.EntityConverters;
import com.elbuensabor.usuario.controllers.data.entities.UserEntityUI;
import com.elbuensabor.usuario.data.entities.db.UserEntityDb;
import com.elbuensabor.usuario.data.repository.UserRepository;
import com.elbuensabor.usuario.logic.validators.Result;

@Service
public class RegisterUserUserCase {

    @Autowired
    private UserRepository userRepository;

    public Result<UserEntityUI> execute(
            String nombre,
            String apellido,
            String email,
            String password) {

        Result<UserEntityUI> result;

        try {           
            var userBuilder = UserEntityDb.builder()
                    .nameUser(nombre)
                    .lastNameUser(apellido)
                    .emailUser(email)
                    .passwordUser(password);

            var user = userBuilder.build();
            var userSaved = userRepository.save(user);
            result = Result.success(
                    EntityConverters.userEntityDbToUI(userSaved));

        } catch (Exception e) {
            result = Result.failure(e);
        }

        return result;
    }
  
}
