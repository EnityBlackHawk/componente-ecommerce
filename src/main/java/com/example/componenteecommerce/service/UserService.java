package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.UserDTO;
import com.example.componenteecommerce.dto.create.CreateUserDTO;
import com.example.componenteecommerce.entity.User;
import com.example.componenteecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends GenericService<User, UUID, UserRepository, UserDTO, CreateUserDTO> {

    protected UserService(UserRepository repository) {
        super(repository, User.class, UserDTO.class, CreateUserDTO.class);
    }
}
