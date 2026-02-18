package com.elbuensabor.usuario.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elbuensabor.usuario.controllers.data.entities.UserDTO;
import com.elbuensabor.usuario.logic.usercases.DeleteUserUserCase;
import com.elbuensabor.usuario.logic.usercases.EditUserUserCase;
import com.elbuensabor.usuario.logic.usercases.GetAllUserUserCase;
import com.elbuensabor.usuario.logic.usercases.GetUserByIdUserCase;
import com.elbuensabor.usuario.logic.usercases.RegisterUserUserCase;

@RestController
@RequestMapping("api/users")
public class UserService {

    @Autowired
    private RegisterUserUserCase registerUserUserCase;

    @Autowired
    private GetAllUserUserCase listUserUserCase;

    @Autowired
    private GetUserByIdUserCase getUserByIdUserCase;

    @Autowired
    private EditUserUserCase editUserUserCase;

    @Autowired
    private DeleteUserUserCase deleteUserUserCase;

    @PostMapping(path = "/make-users", consumes = "application/json")
    public ResponseEntity<?> registerUsuario(
            @RequestBody UserDTO usuario) {
        var result = registerUserUserCase.execute(
                usuario.getName(),
                usuario.getLastname(),
                usuario.getEmail(),
                usuario.getPassword());
        return result.fold(
                val -> ResponseEntity.ok(val),
                ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @PostMapping("/modify-user/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id,
            @RequestParam String name,
            @RequestParam String lastName,
            @RequestParam String email) {
        return editUserUserCase.updateUser(id, name, lastName, email).fold(
                ResponseEntity::ok,
                ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> listAll() {
        return listUserUserCase.execute().fold(
                ResponseEntity::ok,
                ex -> ResponseEntity.internalServerError().body(ex.getMessage()));
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> findByIdParam(@PathVariable("id") Integer id) {
        return getUserByIdUserCase.execute(id).fold(
                val -> ResponseEntity.ok(val),
                ex -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

    @PostMapping("/delete-user/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return deleteUserUserCase.deleteUser(id).fold( 
                val -> ResponseEntity.ok("User deleted successfully"),
                ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
