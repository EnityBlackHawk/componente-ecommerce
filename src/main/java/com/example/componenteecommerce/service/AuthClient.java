package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.auth.AuthenticationDto;
import feign.HeaderMap;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "auth-api", url = "${auth-api.url}")
public interface AuthClient {

    @PostMapping("/authenticate")
    AuthenticationDto authenticate(@HeaderMap Map<String, Object> headers);

}
