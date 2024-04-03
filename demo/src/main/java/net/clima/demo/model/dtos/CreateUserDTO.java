package net.clima.demo.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateUserDTO {

    private String name;
    private String password;
    private String email;
}
