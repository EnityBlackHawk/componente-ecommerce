package com.example.componenteecommerce.filter;

import com.example.componenteecommerce.exception.AuthException;
import com.example.componenteecommerce.service.AuthClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.Map;


public class TokenFilter extends OncePerRequestFilter {


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

        var resp = authClient.authenticate(Map.of(HttpHeaders.AUTHORIZATION, authorizationHeader));

        if(resp == null || !resp.getIsValid()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        request.setAttribute("uuid", resp.getUuid());
        filterChain.doFilter(request, response);
    }
}
