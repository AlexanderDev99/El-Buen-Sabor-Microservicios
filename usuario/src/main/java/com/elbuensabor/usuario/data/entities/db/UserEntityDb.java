package com.elbuensabor.usuario.data.entities.db;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntityDb {
    
    @Id
    private String id;

    private String nameUser;
    private String lastNameUser;
    private String emailUser;
    private String passwordUser;
}
