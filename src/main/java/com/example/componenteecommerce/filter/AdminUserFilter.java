package com.example.componenteecommerce.filter;

import com.example.componenteecommerce.dto.auth.AuthenticationDto;
import com.example.componenteecommerce.enums.UserType;
import com.example.componenteecommerce.service.AuthClient;

public class AdminUserFilter extends TokenFilter{

    public AdminUserFilter(AuthClient authClient) {
        super(authClient);
    }

    @Override
    public boolean validate(AuthenticationDto auth) {
        return auth.getUserType().equals(UserType.ADMIN);
    }

}
