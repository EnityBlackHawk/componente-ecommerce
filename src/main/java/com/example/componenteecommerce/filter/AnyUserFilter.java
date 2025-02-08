package com.example.componenteecommerce.filter;

import com.example.componenteecommerce.dto.auth.AuthenticationDto;
import com.example.componenteecommerce.service.AuthClient;

public class AnyUserFilter extends TokenFilter{
    public AnyUserFilter(AuthClient authClient) {
        super(authClient);
    }

    @Override
    public boolean validate(AuthenticationDto auth) {
        return true;
    }
}
