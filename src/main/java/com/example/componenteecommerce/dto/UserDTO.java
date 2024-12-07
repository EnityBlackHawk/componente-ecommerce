package com.example.componenteecommerce.dto;

import com.example.componenteecommerce.enums.UserType;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID id;
    private String name;
    @Email
    private String email;
    private UserType userType;

}
