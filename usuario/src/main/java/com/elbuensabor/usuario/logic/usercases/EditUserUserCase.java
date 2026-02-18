package com.elbuensabor.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.usuario.data.entities.db.UserEntityDb;
import com.elbuensabor.usuario.data.repository.UserRepository;
import com.elbuensabor.usuario.logic.validators.Result;

@Service
public class EditUserUserCase {

    @Autowired
    private UserRepository userRepository;

    public Result<UserEntityDb> updateUser(String id,
            String name, String lastName, String email) {
        try {
            var userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                return Result.failure(new Exception("User not found with ID: " + id));
            }

            UserEntityDb existingUser = userOptional.get();

            var updatedUser = UserEntityDb.builder()
                    .id(existingUser.getId())
                    .nameUser(name)
                    .lastNameUser(lastName)
                    .emailUser(email)
                    .passwordUser(existingUser.getPasswordUser())
                    .build();

            var saved = userRepository.save(updatedUser);
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
