package com.example.componenteecommerce.dto.create;

import com.example.componenteecommerce.enums.UserType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    @Size(min = 8)
    private String password;
    private UserType userType;

}
