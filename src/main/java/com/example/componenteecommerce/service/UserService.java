package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.UserDTO;
import com.example.componenteecommerce.dto.create.CreateUserDTO;
import com.example.componenteecommerce.dto.create.ReplicateUserDTO;
import com.example.componenteecommerce.entity.User;
import com.example.componenteecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService extends GenericService<User, UUID, UserRepository, UserDTO, CreateUserDTO> {

    private final UserQueuedService userQueuedService;
    private final AuthClient authClient;

    protected UserService(UserRepository repository, UserQueuedService userQueuedService, AuthClient authClient) {
        super(repository, User.class, UserDTO.class, CreateUserDTO.class);
        this.userQueuedService = userQueuedService;
        this.authClient = authClient;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> result = repository.findAll();
        return result.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .toList();

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
