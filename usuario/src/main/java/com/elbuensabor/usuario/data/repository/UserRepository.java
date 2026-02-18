package com.elbuensabor.usuario.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elbuensabor.usuario.data.entities.db.UserEntityDb;

public interface UserRepository extends JpaRepository<UserEntityDb, Integer> {

        

}
