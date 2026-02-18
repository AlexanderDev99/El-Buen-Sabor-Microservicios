package com.elbuensabor.usuario.data.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.elbuensabor.usuario.data.entities.db.UserEntityDb;

public interface UserRepository extends MongoRepository<UserEntityDb, String> {

        // Spring deduce la consulta: { "nameUser" : ?0 }
    Optional<UserEntityDb> findByNameUser(String nameUser);

}
