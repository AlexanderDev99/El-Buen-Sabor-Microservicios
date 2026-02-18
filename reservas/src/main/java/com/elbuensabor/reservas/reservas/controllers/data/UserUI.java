package com.elbuensabor.reservas.reservas.controllers.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUI {

    private String nameUser;
    private String lastNameUser;
    private String emailUser;
    private String passwordUser;
    private String passwordConfirm;

}
