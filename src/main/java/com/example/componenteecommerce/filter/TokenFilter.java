package com.example.componenteecommerce.filter;

import com.example.componenteecommerce.dto.auth.AuthenticationDto;
import com.example.componenteecommerce.exception.AuthException;
import com.example.componenteecommerce.service.AuthClient;
import feign.FeignException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.Map;


public abstract class TokenFilter extends OncePerRequestFilter {


    private AuthClient authClient;

    public TokenFilter(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        AuthenticationDto resp;

        try {
           resp = authClient.authenticate(authorizationHeader);
        } catch (FeignException ex) {
            response.sendError(ex.status());
            return;
        }

        if(resp == null || !resp.getIsValid()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if(!validate(resp)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        request.setAttribute("uuid", resp.getUuid());
        filterChain.doFilter(request, response);
    }

    public abstract boolean validate(AuthenticationDto auth);
}
