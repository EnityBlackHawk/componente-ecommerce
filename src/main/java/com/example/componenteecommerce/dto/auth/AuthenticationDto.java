package com.example.componenteecommerce.dto.auth;

import com.example.componenteecommerce.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    private Boolean isValid;
    private UserType userType;
    private UUID uuid;
}
