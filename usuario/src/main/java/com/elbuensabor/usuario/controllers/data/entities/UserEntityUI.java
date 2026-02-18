package com.elbuensabor.usuario.controllers.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityUI {

    private String id;
    private String name;
    private String lastName;
    private String email;

}
