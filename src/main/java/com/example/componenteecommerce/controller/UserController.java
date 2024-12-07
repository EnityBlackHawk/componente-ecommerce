package com.example.componenteecommerce.controller;

import com.example.componenteecommerce.dto.UserDTO;
import com.example.componenteecommerce.dto.create.CreateUserDTO;
import com.example.componenteecommerce.service.UserService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${endpoint.prefix}/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAll() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public UserDTO create(@RequestBody CreateUserDTO user) {
        return userService.create(user);
    }

}
