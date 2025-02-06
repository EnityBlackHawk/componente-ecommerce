package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.UserDTO;
import com.example.componenteecommerce.dto.create.CreateUserDTO;
import com.example.componenteecommerce.dto.create.ReplicateUserDTO;
import com.example.componenteecommerce.entity.User;
import com.example.componenteecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends GenericService<User, UUID, UserRepository, UserDTO, CreateUserDTO> {

    private final UserQueuedService userQueuedService;

    protected UserService(UserRepository repository, UserQueuedService userQueuedService) {
        super(repository, User.class, UserDTO.class, CreateUserDTO.class);
        this.userQueuedService = userQueuedService;

    }

    @Override
    public UserDTO create(CreateUserDTO entity) {
        var user =  repository.save(modelMapper.map(entity, entityClass));
        var dto = modelMapper.map(user, dtoClass);
        var rep = modelMapper.map(user, ReplicateUserDTO.class);
        rep.setUuid(user.getId());
        userQueuedService.sendUser(rep);
        return dto;
    }
}
