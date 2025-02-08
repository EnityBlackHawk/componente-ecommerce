package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.auth.AuthenticationDto;
import feign.HeaderMap;
import feign.Headers;
import feign.Param;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "auth-api", url = "${auth-api.url}")
public interface AuthClient {


    @PostMapping("/authenticate")
    AuthenticationDto authenticate(@RequestHeader("Authorization") String token);

}
