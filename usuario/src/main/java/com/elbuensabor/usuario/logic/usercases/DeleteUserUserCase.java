package com.elbuensabor.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.usuario.data.repository.UserRepository;
import com.elbuensabor.usuario.logic.validators.Result;

@Service
public class DeleteUserUserCase {

    @Autowired
    private UserRepository userRepository;

     public Result<Boolean> deleteUser(Integer id) {
        try {
            if (!userRepository.existsById(id)) {
                return Result.failure(new Exception("User with id " + id + " does not exist."));
            }
            userRepository.deleteById(id);
            return Result.success(true);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
