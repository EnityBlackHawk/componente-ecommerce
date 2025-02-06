package com.example.componenteecommerce.dto.create;

import com.example.componenteecommerce.enums.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplicateUserDTO {

    private UUID uuid;
    private String name;
    private String email;
    private String password;
    private UserType userType;
}
